package com.discordkotlin.api.client

import com.discordkotlin.api.structure.ClientIdentity
import com.discordkotlin.api.structure.guild.Guild
import com.discordkotlin.api.structure.guild.GuildMember
import com.discordkotlin.api.structure.user.User
import com.discordkotlin.core.ext.WEBSOCKET_URL
import com.discordkotlin.core.ext.toJsonNode
import com.discordkotlin.core.ext.toJsonTree
import com.discordkotlin.core.gateway.payload.HelloPayload
import com.discordkotlin.core.gateway.payload.IdentifyPayload
import com.discordkotlin.core.gateway.payload.ReadyPayload
import com.discordkotlin.core.gateway.structure.DispatchEvent
import com.discordkotlin.core.gateway.structure.GatewayMessage
import com.discordkotlin.core.gateway.structure.OpAction
import com.discordkotlin.core.gateway.structure.OpCode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.NullNode
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.sendBlocking
import kotlinx.coroutines.selects.select
import org.http4k.client.WebsocketClient
import org.http4k.core.Uri
import org.http4k.format.Jackson.auto
import org.http4k.websocket.Websocket
import org.http4k.websocket.WsMessage
import org.http4k.websocket.WsStatus
import java.lang.System.currentTimeMillis
import java.time.Duration

class ClientGuardian(
    private val identity: ClientIdentity
) {
    private val lens = WsMessage.auto<GatewayMessage>().toLens()

    private var sequence: Int? = null
    private var sessionId: String? = null
    private fun getSequenceAsNode() = sequence?.let { return@let it.toJsonTree<IntNode>() } ?: NullNode.instance
    private var pingTime: Long = 0L
    private val pingChannel = Channel<Long>()

    private lateinit var guardianScope: CoroutineScope
    private lateinit var user: User

    private val wsMessageChannel = Channel<WsMessage>(Channel.UNLIMITED)
    val readyChannel = BroadcastChannel<ReadyPayload>(1)
    val guildCreateChannel = BroadcastChannel<Guild>(25)
    val guildMemberCreateChannel = BroadcastChannel<GuildMember>(25)

    // TODO should use PolyHandler?
    private val socket = WebsocketClient.nonBlocking(
        Uri.of(WEBSOCKET_URL),
        listOf(identity.getAsHeader()),
        Duration.ofNanos(0),
        onConnect = this::login
    ).apply {
        onClose {
            println(it)
        }
        onError {
            it.printStackTrace()
        }
        onMessage {
            wsMessageChannel.sendBlocking(it)
        }
    }

    private fun dispatch(gatewayMessage: GatewayMessage) {
        socket.send(lens.create(gatewayMessage))
    }

    private fun sendHeartbeat() {
        dispatch(GatewayMessage(OpCode.HEARTBEAT, getSequenceAsNode()))
    }

    private fun login(socket: Websocket) {
        dispatch(GatewayMessage(OpCode.IDENTIFY, IdentifyPayload(identity.token).toJsonTree()))
    }

    suspend fun CoroutineScope.start() = launch {
        println("Starting guardian")
        guardianScope = this

        while (isActive) {
            select<Unit> {
                wsMessageChannel.onReceive {
                    propagateDispatch(it)
                }

                guildCreateChannel.openSubscription()
                    .onReceive {
                        println("Received guild ${it.id}")
                        it.members.ifPresent {
                            it.forEach {
                                guildMemberCreateChannel.offer(it)
                            }
                        }
                    }
            }
        }
    }

    fun stop(status: WsStatus) {
        socket.close(status)
        if (::guardianScope.isInitialized) {
            guardianScope.cancel()
        }
    }

    private fun CoroutineScope.heartbeat(helloPayload: HelloPayload) = launch {
        zombiedConnection(pingChannel)

        while (isActive) {
            println("Sending heartbeat: ${sequence ?: "null"} : Delaying: ${helloPayload.heartBeatInterval}")
            pingTime = currentTimeMillis()
            sendHeartbeat()
            delay(helloPayload.heartBeatInterval)
        }
    }

    private fun CoroutineScope.zombiedConnection(pingChannel: ReceiveChannel<Long>) = launch {
        while (isActive) {
            // TODO close connection if HB-ACK is down
            select<Unit> {
                pingChannel.onReceive {
                    println("Ping is $it ms")
                }
            }
        }
    }

    private fun CoroutineScope.handleDispatch(message: GatewayMessage) = launch {
        with(message) {
            event?.let {
                DispatchEvent.fromString(it)?.let { evt ->
                    when (evt) {
                        DispatchEvent.READY -> {
                            val node = payload.toJsonNode<ReadyPayload>()
                            sessionId = node.sessionId
                            user = node.user
                            readyChannel.send(node)
                        }
                        DispatchEvent.GUILD_CREATE -> {
                            val node = payload.toJsonNode<Guild>()
                            guildCreateChannel.send(node)
                        }
                        else -> {
                        }
                    }
                }
            }
        }
    }

    private fun CoroutineScope.propagateDispatch(wsMsg: WsMessage) = launch {
        val message = lens.extract(wsMsg)

        if (message.sequence != null) {
            sequence = message.sequence
        }

        with(message) {
            if (!opCode.action.hasFlag(OpAction.RECEIVE)) {
                println("Received message that should never have been sent by server :: $opCode")
            } else {
                println(this)
                when (opCode) {
                    OpCode.DISPATCH -> handleDispatch(this)
                    OpCode.HELLO -> heartbeat(payload.toJsonNode())
                    OpCode.HEARTBEAT -> sendHeartbeat()
                    OpCode.HEARTBEAT_ACK -> pingChannel.send(currentTimeMillis() - pingTime)

                    else -> {
                    }
                }
            }
        }
    }
}
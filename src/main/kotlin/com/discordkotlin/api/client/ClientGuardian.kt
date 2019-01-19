package com.discordkotlin.api.client

import com.discordkotlin.api.structure.ClientIdentity
import com.discordkotlin.core.ext.WEBSOCKET_URL
import com.discordkotlin.core.ext.toJsonNode
import com.discordkotlin.core.ext.toJsonTree
import com.discordkotlin.core.gateway.payload.HelloPayload
import com.discordkotlin.core.gateway.payload.IdentifyPayload
import com.discordkotlin.core.gateway.structure.GatewayMessage
import com.discordkotlin.core.gateway.structure.OpAction
import com.discordkotlin.core.gateway.structure.OpCode
import com.fasterxml.jackson.databind.node.IntNode
import com.fasterxml.jackson.databind.node.NullNode
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
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
    private fun getSequenceAsNode() = sequence?.let { return@let it.toJsonTree<IntNode>() } ?: NullNode.instance
    private var pingTime: Long = 0L
    private val pingChannel = Channel<Long>()

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
            // TODO GlobalScope is bad, what do?
            GlobalScope.launch(Dispatchers.IO) {
                propagateDispatch(it)
            }
        }
    }

    private fun dispatch(gatewayMessage: GatewayMessage) {
        socket.send(lens.create(gatewayMessage))
    }

    private fun heartbeatFrame() {
        dispatch(GatewayMessage(OpCode.HEARTBEAT, getSequenceAsNode()))
    }

    private fun login(socket: Websocket) {
        dispatch(
            GatewayMessage(
                OpCode.IDENTIFY, IdentifyPayload(identity.token).toJsonTree()
            )
        )
    }

    fun start() {
        println("Starting guardian")
    }

    fun stop(status: WsStatus) {
        socket.close(status)
    }

    private fun CoroutineScope.heartbeat(helloPayload: HelloPayload) = launch {
        zombiedConnection(pingChannel)

        while (isActive) {
            println("Sending heartbeat: ${sequence ?: "null"} : Delaying: ${helloPayload.heartBeatInterval}")
            pingTime = currentTimeMillis()
            heartbeatFrame()
            delay(helloPayload.heartBeatInterval)
        }
    }

    private fun CoroutineScope.zombiedConnection(pingChannel: ReceiveChannel<Long>) = launch {
        while (isActive) {
            // TODO close connection if HB-ACK is down
            select<Unit> {
                pingChannel.onReceive {
                    println("Ping is $it")
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
                    OpCode.HELLO -> heartbeat(payload.toJsonNode())
                    OpCode.HEARTBEAT -> heartbeatFrame()
                    OpCode.HEARTBEAT_ACK -> pingChannel.send(currentTimeMillis() - pingTime)

                    else -> {
                    }
                }
            }
        }
    }
}
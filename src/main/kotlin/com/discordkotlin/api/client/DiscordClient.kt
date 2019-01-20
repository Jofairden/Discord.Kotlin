package com.discordkotlin.api.client

import com.discordkotlin.api.structure.ClientIdentity
import com.discordkotlin.api.structure.user.User
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consumeEach
import org.http4k.websocket.WsStatus

open class DiscordClient(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    private lateinit var identity: ClientIdentity
    private val guardian by lazy { ClientGuardian(identity) }
    private val disposables = ArrayList<Disposable>()
    private val vault by lazy { ClientVault(this) }

    lateinit var user: User

    fun <T> CoroutineScope.event(evt: BroadcastChannel<T>, fn: (T) -> Unit) = launch(dispatcher) {
        evt.openSubscription().consumeEach(fn)
    }

    fun start(identity: ClientIdentity) = runBlocking {
        startAsync(identity)
    }

    suspend fun startAsync(identity: ClientIdentity) = withContext(dispatcher) {
        this@DiscordClient.identity = identity
        with(guardian) {
            start()
        }
        with(vault) {
            init()
        }

        guardian.readyChannel.openSubscription().receiveOrNull()?.let {
            user = it.user
            println("Received user: ${user.id}")
            delay(2000)
        }
    }

    fun stop(status: WsStatus = WsStatus.NORMAL) {
        disposables.forEach {
            it.dispose()
            assert(it.isDisposed)
        }
        disposables.clear()
        guardian.stop(status)
    }

    val onReady by lazy { guardian.readyChannel }
    val onGuildCreate by lazy { guardian.guildCreateChannel }
    val onGuildMemberCreate by lazy { guardian.guildMemberCreateChannel }
}

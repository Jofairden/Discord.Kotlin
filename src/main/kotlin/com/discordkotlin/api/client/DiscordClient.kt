package com.discordkotlin.api.client

import com.discordkotlin.api.structure.ClientIdentity
import io.reactivex.disposables.Disposable
import org.http4k.websocket.WsStatus

open class DiscordClient {

    private lateinit var identity: ClientIdentity
    private val guardian by lazy { ClientGuardian(identity) }
    private val disposables = ArrayList<Disposable>()

    fun start(identity: ClientIdentity) {
        this.identity = identity
        guardian.start()
    }

    fun stop(status: WsStatus = WsStatus.NORMAL) {
        disposables.forEach {
            it.dispose()
            assert(it.isDisposed)
        }
        disposables.clear()
        guardian.stop(status)
    }
}
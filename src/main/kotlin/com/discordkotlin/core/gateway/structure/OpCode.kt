package com.discordkotlin.core.gateway.structure

import com.fasterxml.jackson.annotation.JsonValue

/**
 * Defines OpCodes that are used in the websocket connection
 * to communicate with the Discord REST API
 */
enum class OpCode(
    @field:JsonValue
    val code: Int,
    val action: OpAction
) {

    /**
     * The Discord REST API dispatched a message to the client
     */
    DISPATCH(0, OpAction.RECEIVE),

    /**
     * A heartbeat frame sent to the server
     * If received, the server requests a heartbeat frame
     */
    HEARTBEAT(1, OpAction.SEND_RECEIVE),

    /**
     * An identify frame sent to the server
     * Contains an authentication header
     * to authenticate the bot
     */
    IDENTIFY(2, OpAction.SEND),

    STATUS_UPDATE(3, OpAction.SEND),

    VOICE_STATE_UPDATE(4, OpAction.SEND),

    VOICE_SERVER_PING(5, OpAction.RECEIVE),

    RESUME(6, OpAction.SEND),

    RECONNECT(7, OpAction.RECEIVE),

    REQUEST_GUILD_MEMBERS(8, OpAction.SEND),

    INVALID_SESSION(9, OpAction.RECEIVE),

    HELLO(10, OpAction.RECEIVE),

    HEARTBEAT_ACK(11, OpAction.RECEIVE)

}

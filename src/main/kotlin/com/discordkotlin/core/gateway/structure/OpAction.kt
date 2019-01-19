package com.discordkotlin.core.gateway.structure

import com.fasterxml.jackson.annotation.JsonValue
import kotlin.experimental.or

/**
 * Defines the action an OpCode is associated with
 */
enum class OpAction(
    @field:JsonValue
    val bits: Byte
) {
    RECEIVE(1 shl 0),
    SEND(1 shl 1),
    SEND_RECEIVE(SEND.bits or RECEIVE.bits);

    fun hasFlag(other: OpAction) = this.bits or other.bits == other.bits
}
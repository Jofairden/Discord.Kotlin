package com.discordkotlin.api.structure.channel

import com.fasterxml.jackson.annotation.JsonValue

enum class MessageActivityType(
    @field:JsonValue
    val type: Int
) {
    JOIN(1),
    SPECTATE(2),
    LISTEN(3),
    JOIN_REQUEST(5);
}
package com.discordkotlin.api.structure.channel

import com.fasterxml.jackson.annotation.JsonValue

enum class MessageType(
    @field:JsonValue
    val type: Int
) {
    DEFAULT(0),
    RECIPIENT_ADD(1),
    RECIPIENT_REMOVE(2),
    CALL(3),
    CHANNEL_NAME_CHANGE(4),
    CHANNEL_ICON_CHANGE(5),
    CHANNEL_PINNED_MESSAGE(6),
    GUILD_MEMBER_JOIN(7);
}
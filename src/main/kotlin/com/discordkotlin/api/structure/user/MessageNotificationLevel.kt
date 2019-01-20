package com.discordkotlin.api.structure.user

import com.fasterxml.jackson.annotation.JsonValue

enum class MessageNotificationLevel(
    @field:JsonValue
    val level: Int
) {
    ALL_MESSAGES(0),
    ONLY_MENTIONS(1);
}
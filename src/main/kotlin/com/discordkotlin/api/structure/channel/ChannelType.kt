package com.discordkotlin.api.structure.channel

import com.fasterxml.jackson.annotation.JsonValue

enum class ChannelType(
    @field:JsonValue
    val type: Int
) {
    GUILD_TEXT(0),
    DM(1),
    GUILD_VOICE(2),
    GROUP_DM(3),
    GUILD_CATEGORY(4)
}
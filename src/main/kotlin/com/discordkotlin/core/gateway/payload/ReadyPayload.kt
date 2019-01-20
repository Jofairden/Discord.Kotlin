package com.discordkotlin.core.gateway.payload

import com.discordkotlin.api.structure.guild.PartialGuild
import com.discordkotlin.api.structure.user.User
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class ReadyPayload(

    @JsonProperty("v")
    val protocol: Int,

    @JsonProperty("user")
    val user: User,

    @JsonProperty("private_channels")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val privateChannels: List<String?>,

    @JsonProperty("guilds")
    val guilds: List<PartialGuild>,

    @JsonProperty("session_id")
    val sessionId: String,

    @JsonProperty("_trace")
    val _trace: List<String>
)


package com.discordkotlin.api.structure.guild

import com.discordkotlin.api.structure.user.User
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class GuildMember(

    @JsonProperty("user")
    val user: User,

    @JsonProperty("nick")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val nickName: Optional<String>,

    @JsonProperty("roles")
    val roles: List<String>,

    @JsonProperty("joined_at")
    val joinedAt: Date,

    @JsonProperty("deaf")
    val deafened: Boolean,

    @JsonProperty("mute")
    val muted: Boolean

)
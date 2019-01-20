package com.discordkotlin.api.structure.voice

import com.fasterxml.jackson.annotation.JsonProperty

data class PartialVoiceState(

    @JsonProperty("channel_id")
    val channelId: String?,

    @JsonProperty("user_id")
    val userId: String,

//    @JsonProperty("member")
//    val guildMember : JsonNode,

    @JsonProperty("session_id")
    val sessionId: String,

    @JsonProperty("deaf")
    val deafened: Boolean,

    @JsonProperty("mute")
    val muted: Boolean,

    @JsonProperty("self_deaf")
    val selfDeafened: Boolean,

    @JsonProperty("self_mute")
    val selfMuted: Boolean,

    @JsonProperty("supress")
    val isSuppressed: Boolean
)
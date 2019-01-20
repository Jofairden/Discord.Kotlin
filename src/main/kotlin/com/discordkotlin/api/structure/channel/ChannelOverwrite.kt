package com.discordkotlin.api.structure.channel

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

data class ChannelOverwrite(

    @JsonProperty("id")
    val id: String, // snowflake

    // TODO map to ChannelOverwriteType?
    @JsonProperty("type")
    val type: String,

    @JsonProperty("allow")
    val allow: Int,

    @JsonProperty("deny")
    val deny: Int

)

enum class ChannelOverwriteType(
    @field:JsonValue
    val type: String
) {
    ROLE("role"),
    MEMBER("member");
}
package com.discordkotlin.api.structure.guild

import com.fasterxml.jackson.annotation.JsonProperty

data class GuildRole(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("color")
    val color: Int,

    @JsonProperty("hoist")
    val hoisted: Boolean,

    @JsonProperty("position")
    val position: Int,

    @JsonProperty("permissions")
    val permissions: Int,

    @JsonProperty("managed")
    val managed: Boolean,

    @JsonProperty("mentionable")
    val mentionable: Boolean

)
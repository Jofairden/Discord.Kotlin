package com.discordkotlin.api.structure.guild

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * A partial guild object.
 * Represents an Offline Guild,
 * or a Guild whose information has not
 * been provided through Guild Create
 * events during the Gateway connect.
 */
data class PartialGuild(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("unavailable")
    val unavailable: Boolean = true
)
package com.discordkotlin.api.structure.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

enum class UserFlags(
    @JsonValue
    @JsonProperty("value")
    val bits: Int
) {
    NONE(0),
    HYPESQUAD_EVENTS(1 shl 2),
    HYPESQUAD_BRAVERY(1 shl 6),
    HYPESQUAD_BRILLIANCE(1 shl 7),
    HYPESQUAD_BALANCE(1 shl 8),
}
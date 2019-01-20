package com.discordkotlin.api.structure.user

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue

enum class PremiumType(
    @JsonValue
    @JsonProperty("value")
    val value: Int
) {
    NITRO_CLASSIC(1),
    NITRO(2)
}
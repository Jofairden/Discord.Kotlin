package com.discordkotlin.api.structure.guild

import com.fasterxml.jackson.annotation.JsonValue

enum class VerificationLevel(
    @field:JsonValue
    val level: Int
) {
    NONE(0),
    LOW(1),
    MEDIUM(2),
    HIGH(3),
    VERY_HIGH(4);
}
package com.discordkotlin.api.structure.user

import com.fasterxml.jackson.annotation.JsonValue

enum class MFALevel(
    @field:JsonValue
    val level: Int
) {
    NONE(0),
    ELEVATED(1);
}
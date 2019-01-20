package com.discordkotlin.api.structure.guild

import com.fasterxml.jackson.annotation.JsonValue

enum class ExplicitContentFilterLevel(
    @field:JsonValue
    val level: Int
) {
    DISABLED(0),
    MEMBERS_WITHOUT_ROLES(1),
    ALL_MEMBERS(2);
}
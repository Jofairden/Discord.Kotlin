package com.discordkotlin.api.structure

enum class AuthType(
    val representation: String
) {
    BOT("Bot"),
    BEARER("Bearer")
}
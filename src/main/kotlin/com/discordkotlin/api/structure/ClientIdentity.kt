package com.discordkotlin.api.structure

data class ClientIdentity(
    val token: String,
    val authType: AuthType
) {
    fun getAsHeader() = Pair("Authentication", "${authType.representation} $token")
}
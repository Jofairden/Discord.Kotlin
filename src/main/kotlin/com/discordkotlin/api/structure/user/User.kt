package com.discordkotlin.api.structure.user

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class User(
    @JsonProperty("id")
    val id: String,

    @JsonProperty("username")
    val username: String,

    @JsonProperty("discriminator")
    val discriminator: Long,

    @JsonProperty("avatar")
    val avatarHash: String?,

    @JsonProperty("bot")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val isBot: Optional<Boolean> = Optional.of(false),

    @JsonProperty("mfa_enabled")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val twoFactorAuth: Optional<Boolean>,

    @JsonProperty("locale")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val locale: Optional<String>,

    @JsonProperty("verified")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val emailVerified: Optional<Boolean> = Optional.of(false),

    @JsonProperty("email")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val email: Optional<String>,

    @JsonProperty("flags")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val flags: Optional<UserFlags> = Optional.of(UserFlags.NONE),

    @JsonProperty("premium_type")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val nitro: Optional<PremiumType>
)
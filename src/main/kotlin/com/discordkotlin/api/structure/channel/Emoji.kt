package com.discordkotlin.api.structure.channel

import com.discordkotlin.api.structure.user.User
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class Emoji(

    @JsonProperty("id")
    val id: String?,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("roles")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val roleIds: Optional<List<String>>,

    @JsonProperty("user")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val user: Optional<User>,

    @JsonProperty("require_colons")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val requireColons: Optional<Boolean>,

    @JsonProperty("managed")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val managed: Optional<Boolean>,

    @JsonProperty("animated")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val animated: Optional<Boolean>

)
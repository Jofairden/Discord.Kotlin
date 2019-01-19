package com.discordkotlin.core.gateway.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import io.reactivex.annotations.NonNull

data class IdentifyPayload(

    @JsonProperty("token")
    val token: String,

    @JsonProperty("properties")
    val properties: IdentifyProperties = IdentifyProperties(),

    @JsonProperty("compress")
    val compress: Boolean = false,

    @JsonProperty("large_threshold")
    val largeThreshold: Int = 250,

    @JsonProperty("shard")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val shards: List<Int>? = null,

    @JsonProperty("presence")
    val presence: List<String>? = null
)

data class IdentifyProperties(
    @JsonProperty("\$os")
    @NonNull
    val os: String = System.getProperty("os.name"),

    @JsonProperty("\$browser")
    @NonNull
    val browser: String = "discord.kotlin",

    @JsonProperty("\$device")
    @NonNull
    val device: String = "discord.kotlin"
)
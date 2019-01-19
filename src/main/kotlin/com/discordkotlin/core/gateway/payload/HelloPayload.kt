package com.discordkotlin.core.gateway.payload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

data class HelloPayload(
    @JsonProperty("heartbeat_interval")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val heartBeatInterval: Long,

    @JsonProperty("_trace")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val _trace: List<String>
)
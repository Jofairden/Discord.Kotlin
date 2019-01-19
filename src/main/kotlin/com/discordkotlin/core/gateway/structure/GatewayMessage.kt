package com.discordkotlin.core.gateway.structure

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode

data class GatewayMessage(

    @JsonProperty("op")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val opCode: OpCode,

    @JsonProperty("d")
    @JsonInclude(JsonInclude.Include.ALWAYS)
    val payload: JsonNode,

    @JsonProperty("s")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val sequence: Int? = null, // Only for OpCode 0

    @JsonProperty("t")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val event: String? = null // Only for OpCode 0

)
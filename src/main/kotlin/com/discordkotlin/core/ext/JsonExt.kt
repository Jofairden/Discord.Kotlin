package com.discordkotlin.core.ext

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

internal val jsonMapper = ObjectMapper()
    .findAndRegisterModules()
    .registerModule(JavaTimeModule())//Parse ISO8061 Dates
    .registerModule(Jdk8Module())//Allows treating Optionals better
    .registerKotlinModule()
    .setSerializationInclusion(JsonInclude.Include.NON_NULL)
    .enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES)
    .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
    .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT)
    .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE)!!

internal inline fun <reified T> String.toJsonNode(): T = jsonMapper.readValue(this)

internal inline fun <reified T : JsonNode> Any.toJsonTree(): T = jsonMapper.valueToTree(this)

internal inline fun <reified T> JsonNode.toJsonNode(): T = this.toString().toJsonNode()

internal fun JsonNode.toJson(): JsonNode = jsonMapper.valueToTree(this)

internal fun Any.writeAsJson() = jsonMapper.writeValueAsString(this)
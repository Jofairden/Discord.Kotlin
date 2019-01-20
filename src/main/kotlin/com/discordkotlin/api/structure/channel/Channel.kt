package com.discordkotlin.api.structure.channel

import com.discordkotlin.api.structure.user.User
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.util.*

data class Channel(

    @JsonProperty("id")
    val id: String, // snowflake

    @JsonProperty("type")
    val type: ChannelType,

    @JsonProperty("guild_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val guildId: Optional<String>,

    @JsonProperty("position")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val position: Optional<Int>,

    @JsonProperty("permission_overwrites")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val permissionOverrides: Optional<JsonNode>,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("topic")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val topic: Optional<String?>,

    @JsonProperty("nsfw")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val nsfw: Optional<Boolean>,

    @JsonProperty("last_message_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val lastMessageId: Optional<String?>, // snowflake

    @JsonProperty("bitrate")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val bitRate: Optional<Int>,

    @JsonProperty("user_limit")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val userLimit: Optional<Int>,

    @JsonProperty("rate_limit_per_user")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val rateLimitPerUser: Optional<Int>,

    @JsonProperty("recipients")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val recipients: Optional<List<User>>,

    @JsonProperty("icon")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val iconHash: Optional<String?>,

    @JsonProperty("owner_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val ownerId: Optional<String>, // snowflake

    @JsonProperty("application_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val applicationId: Optional<String>, // snowflake

    @JsonProperty("parent_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val parentId: Optional<String?>, // snowflake

    @JsonProperty("last_pin_timestamp")
    val lastPinTimestamp: Optional<Date?>

)
package com.discordkotlin.api.structure.guild

import com.discordkotlin.api.structure.channel.Channel
import com.discordkotlin.api.structure.channel.Emoji
import com.discordkotlin.api.structure.user.MFALevel
import com.discordkotlin.api.structure.user.MessageNotificationLevel
import com.discordkotlin.api.structure.voice.PartialVoiceState
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.util.*

data class Guild(

    @JsonProperty("id")
    val id: String,

    @JsonProperty("name")
    val name: String,

    @JsonProperty("icon")
    val iconHash: String?,

    @JsonProperty("splash")
    val splashHash: String?,

    @JsonProperty("owner")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val isOwner: Optional<Boolean>,

    @JsonProperty("owner_id")
    val ownerId: String,

    @JsonProperty("permissions")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val permissions: Optional<Int>,

    @JsonProperty("region")
    val regionId: String,

    @JsonProperty("afk_channel_id")
    val afkChannelId: String?,

    @JsonProperty("afk_timeout")
    val afkTimeoutSeconds: Int,

    @JsonProperty("embed_enabled")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val isEmbeddable: Optional<Boolean>,

    @JsonProperty("embed_channel_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val embedChannelId: Optional<String>,

    @JsonProperty("verification_level")
    val verificationLevel: VerificationLevel,

    @JsonProperty("default_message_notifications")
    val defaultMessageNotificationLevel: MessageNotificationLevel,

    @JsonProperty("explicit_content_filter")
    val explicitContentFilterLevel: ExplicitContentFilterLevel,

    @JsonProperty("roles")
    val roles: List<GuildRole>,

    @JsonProperty("emojis")
    val emojis: List<Emoji>,

    @JsonProperty("features")
    val features: List<String>,

    @JsonProperty("mfa_level")
    val mfaLevel: MFALevel,

    @JsonProperty("application_id")
    val applicationId: String?,

    @JsonProperty("widget_enabled")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val widgetEnabled: Optional<Boolean>,

    @JsonProperty("widget_channel_id")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val widgetChannelId: Optional<String>,

    @JsonProperty("system_channel_id")
    val systemChannelId: String?,

    // GUILD_CREATE only
    @JsonProperty("joined_at")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val creationDate: Optional<Date>,

    // GUILD_CREATE only
    @JsonProperty("large")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val isLarge: Optional<Boolean>,

    // GUILD_CREATE only
    @JsonProperty("unavailable")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val isUnavailable: Optional<Boolean>,

    // GUILD_CREATE only
    @JsonProperty("member_count")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val memberCount: Optional<Int>,

    // GUILD_CREATE only
    @JsonProperty("voice_states")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val voiceStates: Optional<List<PartialVoiceState>>,

    // GUILD_CREATE only
    @JsonProperty("members")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val members: Optional<List<GuildMember>>,

    // GUILD_CREATE only
    @JsonProperty("channels")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val channels: Optional<List<Channel>>,

    // GUILD_CREATE only
    @JsonProperty("presences")
    @JsonInclude(JsonInclude.Include.NON_ABSENT)
    val presences: Optional<List<JsonNode>>
)
package com.discordkotlin.api.structure.channel

import com.discordkotlin.api.structure.guild.GuildRole
import com.discordkotlin.api.structure.user.User
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.JsonNode
import java.util.*

data class ChannelMessage(

    val id: String,

    @JsonProperty("channel_id")
    val channelId: String,

    @JsonProperty("guild_id")
    val guildId: Optional<String>,

    val author: User,

    // TODO
    @JsonProperty("member")
    val guildMember: Optional<JsonNode>,

    val content: String,

    val timestamp: Date,

    @JsonProperty("edited_timestamp")
    val editedTimestamp: Date?,

    val tss: Boolean,

    @JsonProperty("mention_everyone")
    val mentionEveryone: Boolean,

    val mentions: List<User>,

    @JsonProperty("mention_roles")
    val mentionRoles: List<GuildRole>,

    val attachments: List<JsonNode>,

    // TODO
    val embeds: List<JsonNode>,

    // TODO
    val reactions: Optional<List<JsonNode>>,

    val nonce: Optional<String?>,

    val pinned: Boolean,

    val webhookId: Optional<String>,

    val type: MessageType,

    // TODO
    val activity: Optional<JsonNode>,

    // TODO
    val application: Optional<JsonNode>

)

package com.discordkotlin.api.client

import com.discordkotlin.api.structure.guild.Guild
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ClientVault(
    private val client: DiscordClient
) {
    val guilds = GuildVault()

    fun CoroutineScope.init() = launch {
        println("Initializing data vault...")

        with(client) {
            event(onGuildCreate) {
                println("Adding guild ${it.name} to vault")
                guilds.guilds.add(it)
            }
        }
    }
}

class GuildVault() {
    val guilds = ArrayList<Guild>()
}
package com.example

import com.discordkotlin.api.client.DiscordClient
import com.discordkotlin.api.structure.AuthType
import com.discordkotlin.api.structure.ClientIdentity
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.File

fun main() = runBlocking {
    println("Starting application...")

    launch {
        println("Launching bot")
        val myBot = MyBot()
        myBot.runBot()
        println("Bot is running...")
        while (this.isActive);
    }.join()
}

class MyBot : DiscordClient() {
    fun runBot() {
        start(
            ClientIdentity(
                getBotToken(),
                AuthType.BOT
            )
        )
    }

    private fun getBotToken(): String {
        val classLoader = MyBot::class.java.classLoader
        val file = File(classLoader.getResource("bot.token").file)

        if (!file.canRead()) {
            throw RuntimeException("Unable to read bot.token file")
        }

        return file.readText()
    }
}


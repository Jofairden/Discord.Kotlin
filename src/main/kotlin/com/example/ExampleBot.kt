package com.example

import com.discordkotlin.api.client.DiscordClient
import com.discordkotlin.api.structure.AuthType
import com.discordkotlin.api.structure.ClientIdentity
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield
import java.io.File

@ObsoleteCoroutinesApi
fun main() = runBlocking<Unit> {
    println("Starting application...")

    val myBot = MyBot()

    launch {
        println("Launching bot")
        myBot.runBotAsync()
    }
    yield()

    with(myBot) {
        event(onReady) {
            println("My bot is ready! I am: ${it.user.username}")
        }
        event(onGuildCreate) {
            println("My bot is in guild: ${it.name}")
        }
        event(onGuildMemberCreate) {
            println("My bot received member: ${it.user.username}")
        }
    }
}

class MyBot : DiscordClient() {
    fun runBot() = start(
        ClientIdentity(
            getBotToken(),
            AuthType.BOT
        )
    )

    suspend fun runBotAsync() = startAsync(
        ClientIdentity(
            getBotToken(),
            AuthType.BOT
        )
    )

    private fun getBotToken(): String {
        val classLoader = MyBot::class.java.classLoader
        val file = File(classLoader.getResource("bot.token").file)

        if (!file.canRead()) {
            throw RuntimeException("Unable to read bot.token file")
        }

        return file.readText()
    }
}




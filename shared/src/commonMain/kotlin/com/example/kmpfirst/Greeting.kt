package com.example.kmpfirst

import RocketLaunch
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class Greeting {
    private val platform: Platform = getPlatform()

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    @Throws(Exception::class)
    suspend fun greet(): String {
        val rockets = httpClient.get("https://api.spacexdata.com/v4/launches").body<List<RocketLaunch>>()
        val lastSuccessLaunch = rockets.last { it.launchSuccess == true }
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} left until New Year! 🎆" +
                "\nThe last successful launch was ${lastSuccessLaunch.launchDateUTC} 🚀"
    }
}
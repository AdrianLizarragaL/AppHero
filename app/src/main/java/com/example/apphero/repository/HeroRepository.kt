package com.example.apphero.repository

import com.example.apphero.services.RetrofitClient
import com.example.apphero.services.WebService

class HeroRepository {
    private var apiService: WebService? = null

    init {
        apiService = RetrofitClient.getClient?.create(WebService::class.java)
    }

    suspend fun getHero() = apiService?.getHeroes()
}
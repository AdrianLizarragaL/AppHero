package com.example.apphero.services

import com.example.apphero.model.HeroDataModel
import com.example.apphero.model.HeroResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("all.json")
    suspend fun getHeroes(): Response<List<HeroDataModel>>?
}
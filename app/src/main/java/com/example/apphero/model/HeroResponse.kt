package com.example.apphero.model

import com.google.gson.annotations.SerializedName

data class HeroResponse (
    val hero: MutableList<HeroDataModel> = mutableListOf()
)

data class HeroDataModel(
    val id: Long,
    val name: String = "",
    val images: Images,
    val appearance: Appearance,
    val biography: Biography
)

data class Images(
    val sm: String
)

data class Appearance(
    val gender: String,
    val race: String
)

data class Biography(
    val fullName: String,
    val placeOfBirth: String,
    val publisher: String
)
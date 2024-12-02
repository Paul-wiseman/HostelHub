package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class CityDto(
    @Json(name = "country")
    val country: String?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "idCountry")
    val idCountry: Int?,
    @Json(name = "name")
    val name: String?
)
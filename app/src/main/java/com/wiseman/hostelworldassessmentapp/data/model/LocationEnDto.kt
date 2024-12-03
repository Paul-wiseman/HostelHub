package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class LocationEnDto(
    @Json(name = "city")
    val city: CityDto?,
    @Json(name = "region")
    val region: String?
)
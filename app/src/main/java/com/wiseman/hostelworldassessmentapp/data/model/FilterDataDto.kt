package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class FilterDataDto(
    @Json(name = "highestPricePerNight")
    val highestPricePerNight: HighestPricePerNightDto?,
    @Json(name = "lowestPricePerNight")
    val lowestPricePerNight: LowestPricePerNightDto?
)
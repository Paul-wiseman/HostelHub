package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class FilterDataDto(
    @Json(name = "highestPricePerNight")
    val highestPricePerNight: HighestPricePerNightDto?,
    @Json(name = "lowestPricePerNight")
    val lowestPricePerNight: LowestPricePerNightDto?
)
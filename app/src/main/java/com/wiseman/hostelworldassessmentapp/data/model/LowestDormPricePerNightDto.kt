package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class LowestDormPricePerNightDto(
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "value")
    val value: String?
)
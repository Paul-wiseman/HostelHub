package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class OriginalLowestAveragePrivatePricePerNightDto(
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "value")
    val value: String?
)
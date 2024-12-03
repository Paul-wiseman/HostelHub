package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class OriginalLowestAveragePricePerNightDto(
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "value")
    val value: String?
)
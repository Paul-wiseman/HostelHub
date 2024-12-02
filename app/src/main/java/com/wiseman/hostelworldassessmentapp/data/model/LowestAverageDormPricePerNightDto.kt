package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class LowestAverageDormPricePerNightDto(
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "original")
    val original: String?,
    @Json(name = "promotions")
    val promotions: Promotions?,
    @Json(name = "value")
    val value: String?
)
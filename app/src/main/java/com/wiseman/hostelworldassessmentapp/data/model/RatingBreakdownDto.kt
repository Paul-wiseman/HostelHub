package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class RatingBreakdownDto(
    @Json(name = "average")
    val average: Int?,
    @Json(name = "clean")
    val clean: Int?,
    @Json(name = "facilities")
    val facilities: Int?,
    @Json(name = "fun")
    val funx: Int?,
    @Json(name = "location")
    val location: Int?,
    @Json(name = "ratingsCount")
    val ratingsCount: Int?,
    @Json(name = "security")
    val security: Int?,
    @Json(name = "staff")
    val staff: Int?,
    @Json(name = "value")
    val value: Int?
)
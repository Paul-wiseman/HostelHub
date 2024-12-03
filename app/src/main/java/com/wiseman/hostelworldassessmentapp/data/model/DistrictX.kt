package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class DistrictX(
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?
)
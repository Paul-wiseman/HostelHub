package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class DistrictDto(
    @Json(name = "id")
    val id: String?,
    @Json(name = "name")
    val name: String?
)
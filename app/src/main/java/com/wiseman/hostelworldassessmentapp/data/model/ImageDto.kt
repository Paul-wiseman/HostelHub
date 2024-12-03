package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @Json(name = "prefix")
    val prefix: String?,
    @Json(name = "suffix")
    val suffix: String?
)
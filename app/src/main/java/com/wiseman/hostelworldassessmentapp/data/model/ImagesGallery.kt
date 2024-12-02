package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class ImagesGalleryDto(
    @Json(name = "prefix")
    val prefix: String?,
    @Json(name = "suffix")
    val suffix: String?
)
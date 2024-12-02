package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class Promotion(
    @Json(name = "discount")
    val discount: Int?,
    @Json(name = "id")
    val id: Int?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "stack")
    val stack: Boolean?,
    @Json(name = "type")
    val type: String?
)
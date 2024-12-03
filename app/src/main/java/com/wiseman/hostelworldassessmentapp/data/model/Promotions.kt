package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json
import kotlinx.serialization.Serializable

@Serializable
data class Promotions(
    @Json(name = "promotionsIds")
    val promotionsIds: List<Int?>?,
    @Json(name = "totalDiscount")
    val totalDiscount: String?
)
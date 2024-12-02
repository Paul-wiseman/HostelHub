package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class Promotions(
    @Json(name = "promotionsIds")
    val promotionsIds: List<Int?>?,
    @Json(name = "totalDiscount")
    val totalDiscount: String?
)
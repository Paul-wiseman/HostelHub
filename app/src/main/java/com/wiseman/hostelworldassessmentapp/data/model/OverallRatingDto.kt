package com.wiseman.hostelworldassessmentapp.data.model


import com.squareup.moshi.Json

data class OverallRatingDto(
    @Json(name = "numberOfRatings")
    val numberOfRatings: String?,
    @Json(name = "overall")
    val overall: Int?
)
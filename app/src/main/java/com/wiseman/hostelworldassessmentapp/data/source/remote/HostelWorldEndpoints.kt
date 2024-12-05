package com.wiseman.hostelworldassessmentapp.data.source.remote

import com.wiseman.hostelworldassessmentapp.BuildConfig

/**
 * Object containing the API endpoints for the HostelWorld API.
 *
 * This object defines the base URL and paths for the API endpoints used to fetch
 * available properties and currency exchange rates.
 *
 * The API endpoints follow a RESTful structure, with resources represented by
 * nouns and actions represented by HTTP verbs.
 */
object HostelWorldEndpoints {
    private const val BASE_URL = BuildConfig.BASE_URL
    private const val PROPERTIES_PATH = "a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563138541/properties.json"
    private const val RATES_PATH = "16e87e40ca7b9650aa8e1b936f23e14e/raw/15efd901b57c2b66bf0201ee7aa9aa2edc6df779/rates.json"

    const val PROPERTIES_ENDPOINT = "$BASE_URL$PROPERTIES_PATH"
    const val RATES_ENDPOINT = "$BASE_URL$RATES_PATH"
}
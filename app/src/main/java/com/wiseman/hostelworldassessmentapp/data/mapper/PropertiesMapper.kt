package com.wiseman.hostelworldassessmentapp.data.mapper

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.PropertyDto
import com.wiseman.hostelworldassessmentapp.domain.model.Amenity
import com.wiseman.hostelworldassessmentapp.domain.model.AvailableProperties
import com.wiseman.hostelworldassessmentapp.domain.model.Facility
import com.wiseman.hostelworldassessmentapp.domain.model.FreeCancellation
import com.wiseman.hostelworldassessmentapp.domain.model.ImagesGallery
import com.wiseman.hostelworldassessmentapp.domain.model.Location
import com.wiseman.hostelworldassessmentapp.domain.model.LowestPricePerNight
import com.wiseman.hostelworldassessmentapp.domain.model.OverallRating
import com.wiseman.hostelworldassessmentapp.domain.model.Property

fun AvailablePropertiesDto.toDomainModel(): AvailableProperties =
    AvailableProperties(
        location = Location(
            country = location?.city?.country,
            name = location?.city?.name,
            idCountry = location?.city?.idCountry

        ),
        properties = properties?.map { it: PropertyDto ->
            Property(
                fullAddress = "${it.address1}, ${location?.city?.name}, ${location?.city?.country}",
                address1 = it.address1,
                address2 = it.address2,
                facilities = it.facilities?.map { facilityDto ->
                    Facility(
                        facilities = facilityDto.facilities?.map { amenity ->
                            Amenity(name = amenity.name)
                        },
                        name = facilityDto.name

                    )
                },
                freeCancellation = FreeCancellation(
                    description = it.freeCancellation?.description,
                    label = it.freeCancellation?.label
                ),
                freeCancellationAvailable = it.freeCancellationAvailable,
                freeCancellationAvailableUntil = it.freeCancellationAvailableUntil,
                id = it.id,
                imagesGallery = it.imagesGallery?.map { imageGallary ->
                    ImagesGallery(
                        imageUrl = "https://${imageGallary.prefix}${imageGallary.suffix}"
                    )
                } ?: listOf(),
                isFeatured = it.isFeatured,
                isPromoted = it.isPromoted,
                name = it.name,
                overallRating = OverallRating(
                    numberOfRatings = it.overallRating?.numberOfRatings,
                    overall = it.overallRating?.overall ?: 0
                ),
                overview = it.overview,
                position = it.position,
                type = it.type,
                veryPopular = it.veryPopular,
                lowestPricePerNight = it.lowestPricePerNight?.currency?.let { currency ->
                    LowestPricePerNight(
                        currency = currency,
                        value = it.lowestPricePerNight.value

                    )
                },
            )
        }
    )
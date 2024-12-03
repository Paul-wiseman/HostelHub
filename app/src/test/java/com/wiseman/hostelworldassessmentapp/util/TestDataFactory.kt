package com.wiseman.hostelworldassessmentapp.util

import com.wiseman.hostelworldassessmentapp.data.model.AvailablePropertiesDto
import com.wiseman.hostelworldassessmentapp.data.model.CityDto
import com.wiseman.hostelworldassessmentapp.data.model.CurrencyExchangeRatesDto
import com.wiseman.hostelworldassessmentapp.data.model.ExchangeRatesDto
import com.wiseman.hostelworldassessmentapp.data.model.FacilityDto
import com.wiseman.hostelworldassessmentapp.data.model.FacilityX
import com.wiseman.hostelworldassessmentapp.data.model.FilterDataDto
import com.wiseman.hostelworldassessmentapp.data.model.FreeCancellationDto
import com.wiseman.hostelworldassessmentapp.data.model.HighestPricePerNightDto
import com.wiseman.hostelworldassessmentapp.data.model.ImageDto
import com.wiseman.hostelworldassessmentapp.data.model.ImagesGalleryDto
import com.wiseman.hostelworldassessmentapp.data.model.LocationDto
import com.wiseman.hostelworldassessmentapp.data.model.LocationEnDto
import com.wiseman.hostelworldassessmentapp.data.model.LowestAverageDormPricePerNightDto
import com.wiseman.hostelworldassessmentapp.data.model.LowestAveragePricePerNightDto
import com.wiseman.hostelworldassessmentapp.data.model.LowestAveragePrivatePricePerNightDto
import com.wiseman.hostelworldassessmentapp.data.model.LowestPricePerNightDto
import com.wiseman.hostelworldassessmentapp.data.model.OverallRatingDto
import com.wiseman.hostelworldassessmentapp.data.model.Pagination
import com.wiseman.hostelworldassessmentapp.data.model.Promotions
import com.wiseman.hostelworldassessmentapp.data.model.PropertyDto
import com.wiseman.hostelworldassessmentapp.data.model.RatingBreakdownDto

object TestDataFactory {

    fun getAvailablePropertiesDto(): AvailablePropertiesDto {
        return AvailablePropertiesDto(
            properties = listOf(
                PropertyDto(
                    id = 100,
                    isPromoted = false,
                    hbid = 0,
                    name = "Abbey Court",
                    starRating = 0,
                    overallRating = OverallRatingDto( "11133",85),
                    ratingBreakdown = RatingBreakdownDto(
                        ratingsCount = 46,
                        security = 84,
                        location = 95,
                        staff = 88,
                        funx = 80,
                        clean = 83,
                        facilities = 83,
                        value = 83,
                        average = 85
                    ),
                    latitude = 53.3474366,
                    longitude = -6.2603459,
                    isFeatured = true,
                    type = "HOSTEL",
                    address1 = "29 Bachelors Walk",
                    address2 = "Dublin 1",
                    freeCancellationAvailable = false,
                    freeCancellationAvailableUntil = "2021-11-16T00:00:00+00:00",
                    district = null,
                    districts = listOf(),
                    freeCancellation = FreeCancellationDto(
                        label = "Free Cancellation rates",
                        description = "If you cancel your booking before 23:59... (detailed description)"
                    ),
                    lowestPricePerNight = LowestPricePerNightDto("14.18", "EUR"),
                    lowestPrivatePricePerNight = null,
                    lowestAverageDormPricePerNightDto = LowestAverageDormPricePerNightDto("14.18", "EUR",
                        promotions = null,
                        value = null,),
                    lowestAveragePricePerNightDto = LowestAveragePricePerNightDto(
                        value = "19.13",
                        currency = "EUR",
                        promotions = Promotions(listOf(29013, 24900), "23.50"),
                        original = "25.01"
                    ),
                    lowestAveragePrivatePricePerNight = LowestAveragePrivatePricePerNightDto(
                        value = "74.66",
                        currency = "EUR",
                        promotions = Promotions(listOf(24900), "10.00"),
                        original = "82.96"
                    ),
                    isNew = false,
                    overview = "We're a stone's throw from Temple Bar, O'Connell Bridge, Trinity College...",
                    isElevate = false,
                    hostelworldRecommends = false,
                    distance = null,
                    position = 2,
                    hwExtra = null,
                    fabSort = null,
                    promotions = null,
                    rateRuleViolations = listOf(),
                    fenceDiscount = 24,
                    veryPopular = false,
                    imageDtos = listOf(
                        ImageDto("cloudinary-test.staging.hostelworld.com/propertyimages/1/100/36", ".jpg"),
                        ImageDto("cloudinary-test.staging.hostelworld.com/propertyimages/1/100/qzseav8zdfqpugqjpvlj", ".jpg")
                    ),
                    imagesGallery = listOf(
                        ImagesGalleryDto("res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto", "/v1/propertyimages/1/100/36.jpg"),
                        ImagesGalleryDto("res.cloudinary.com/test-hostelworld-com/image/upload/f_auto,q_auto", "/v1/propertyimages/1/100/qzseav8zdfqpugqjpvlj")
                    ),
                    facilities = listOf(
                        FacilityDto(
                            name = "Free",
                            id = "FACILITYCATEGORYFREE",
                            facilities = listOf(
                                FacilityX(name = "Linen Included", id = "LINENINCLUDED"),
                                FacilityX(name = "Free City Maps", id = "FREECITYMAPS")
                            )
                        )
                    ),
                    lowestDormPricePerNightDto = null,
                    originalLowestAverageDormPricePerNightDto = null,
                    originalLowestAveragePricePerNightDto = null,
                    originalLowestAveragePrivatePricePerNightDto = null,
                    stayRuleViolations = listOf()
                )
            ),
            location = LocationDto(
                city = CityDto(id = 5, name = "Dublin", idCountry = 103, country = "Ireland"),
                region = null
            ),
            locationEn = LocationEnDto(
                city = CityDto(id = 5, name = "Dublin", idCountry = 103, country = "Ireland"),
                region = null
            ),
            filterData = FilterDataDto(
                highestPricePerNight = HighestPricePerNightDto("999.00", "EUR"),
                lowestPricePerNight = LowestPricePerNightDto("14.18", "EUR")
            ),
            sortOrder = null,
            pagination = Pagination(next = "", prev = "", numberOfPages = 1, totalNumberOfItems = 20)
        )
    }

    fun getCurrencyExchangeRatesDto(): CurrencyExchangeRatesDto = CurrencyExchangeRatesDto(
        baseCurrency = "EUR",
        date = null,
        historical = null,
        rates = ExchangeRatesDto(
            aED = null,
            aFN = null,
            aLL = null,
            aMD = null,
            aNG = null,
            aOA = null,
            aRS = null,
            aUD = null,
            aWG = null,
            aZN = null,
            bAM = null,
            bBD = null,
            bDT = null,
            bGN = null,
            bHD = null,
            bIF = null,
            bMD = null,
            bND = null,
            bOB = null,
            bRL = null,
            bSD = null,
            bTC = null,
            bTN = null,
            bWP = null,
            bYN = null,
            bYR = null,
            bZD = null,
            cAD = null,
            cDF = null,
            cHF = null,
            cLF = null,
            cLP = null,
            cNY = null,
            cOP = null,
            cRC = null,
            cUC = null,
            cUP = null,
            cVE = null,
            cZK = null,
            dJF = null,
            dKK = null,
            dOP = null,
            dZD = null,
            eGP = null,
            eRN = null,
            eTB = null,
            EUR = 1,
            fJD = null,
            fKP = null,
            GBP = 0.853825,
            gEL = null,
            gGP = null,
            gHS = null,
            gIP = null,
            gMD = null,
            gNF = null,
            gTQ = null,
            gYD = null,
            hKD = null,
            hNL = null,
            hRK = null,
            hTG = null,
            hUF = null,
            iDR = null,
            iLS = null,
            iMP = null,
            iNR = null,
            iQD = null,
            iRR = null,
            iSK = null,
            jEP = null,
            jMD = null,
            jOD = null,
            jPY = null,
            kES = null,
            kGS = null,
            kHR = null,
            kMF = null,
            kPW = null,
            kRW = null,
            kWD = null,
            kYD = null,
            kZT = null,
            lAK = null,
            lBP = null,
            lKR = null,
            lRD = null,
            lSL = null,
            lTL = null,
            lVL = null,
            lYD = null,
            mAD = null,
            mDL = null,
            mGA = null,
            mKD = null,
            mMK = null,
            mNT = null,
            mOP = null,
            mRU = null,
            mUR = null,
            mVR = null,
            mWK = null,
            mXN = null,
            mYR = null,
            mZN = null,
            nAD = null,
            nGN = null,
            nIO = null,
            nOK = null,
            nPR = null,
            nZD = null,
            oMR = null,
            pAB = null,
            pEN = null,
            pGK = null,
            pHP = null,
            pKR = null,
            pLN = null,
            pYG = null,
            qAR = null,
            rON = null,
            rSD = null,
            rUB = null,
            rWF = null,
            sAR = null,
            sBD = null,
            sCR = null,
            sDG = null,
            sEK = null,
            sGD = null,
            sHP = null,
            sLE = null,
            sLL = null,
            sOS = null,
            sRD = null,
            sTD = null,
            sVC = null,
            sYP = null,
            sZL = null,
            tHB = null,
            tJS = null,
            tMT = null,
            tND = null,
            tOP = null,
            tRY = null,
            tTD = null,
            tWD = null,
            tZS = null,
            uAH = null,
            uGX = null,
            USD = 1.088993,
            uYU = null,
            uZS = null,
            vEF = null,
            vES = null,
            vND = null,
            vUV = null,
            wST = null,
            xAF = null,
            xAG = null,
            xAU = null,
            xCD = null,
            xDR = null,
            xOF = null,
            xPF = null,
            yER = null,
            zAR = null,
            zMK = null,
            zMW = null,
            zWL = null
        ),
        requestSuccess = null,
        timestamp = null

    )

}
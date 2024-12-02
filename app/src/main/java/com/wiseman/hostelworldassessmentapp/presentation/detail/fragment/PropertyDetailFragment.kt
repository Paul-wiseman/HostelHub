package com.wiseman.hostelworldassessmentapp.presentation.detail.fragment

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.FragmentPropertyDetailBinding
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.Facility
import com.wiseman.hostelworldassessmentapp.domain.model.Property
import com.wiseman.hostelworldassessmentapp.presentation.adapter.ImageSlideAdapter
import com.wiseman.hostelworldassessmentapp.presentation.home.HomeScreenViewState
import com.wiseman.hostelworldassessmentapp.presentation.viewmodel.PropertyListViewModel
import com.wiseman.hostelworldassessmentapp.util.collectInFragment
import com.wiseman.hostelworldassessmentapp.util.getCurrencySymbolFromCode
import com.wiseman.hostelworldassessmentapp.util.mapValueToScale
import com.wiseman.hostelworldassessmentapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyDetailFragment : Fragment(R.layout.fragment_property_detail) {
    private val propertyListViewModel by activityViewModels<PropertyListViewModel>()
    private val binding by viewBinding(FragmentPropertyDetailBinding::bind)
    private lateinit var viewPagerAdapter: ImageSlideAdapter
    private val args: PropertyDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialViews()
    }

    private fun initialViews() {
        propertyListViewModel.state.collectInFragment { state ->
            state.properties
                ?.find { it.id == args.id }
                ?.let { property ->
                    with(binding) {
                        property.imagesGallery
                            .map { it.imageUrl }
                            .let { list ->
                                viewPagerAdapter = ImageSlideAdapter(root.context, list)
                                viewpager.adapter = viewPagerAdapter
                                indicator.setViewPager(viewpager)
                            }
                        propertyName.text = property.name
                        propertyOverview.text = property.overview
                        location.text = property.fullAddress
                        price.text = String.format(
                            root.context.getString(R.string.price),
                            property.lowestPricePerNight?.currency?.let {
                                getCurrencySymbolFromCode(
                                    it
                                )
                            },
                            property.lowestPricePerNight?.value
                        )
                        rating.text = String.format(
                            root.context.getString(R.string.rating),
                            mapValueToScale(property.overallRating.overall),
                            property.overallRating.numberOfRatings
                        )
                        pricePerNight.setOnClickListener {
                            showStringListDialog(state, property)
                        }
                    }

                    property.facilities?.map { facility: Facility ->
                        val facilityList = facility.facilities?.map { it.name } ?: listOf()
                        createFacilityList(facilityList)
                    }
                }
        }
    }


    private fun createFacilityList(list: List<String?>) {
        list.filterNotNull().forEach { facilityName ->
            with(binding) {
                val chipGroup = amenitiesChipGroup
                val chip = Chip(requireContext())
                chip.text = facilityName
                chip.isClickable = false
                chip.isCloseIconVisible = false
                chipGroup.addView(chip)
            }
        }
    }

    private fun showStringListDialog(screenState: HomeScreenViewState, selectedProperty: Property) {
        val getSupportedCurrency = resources.getStringArray(R.array.supported_currency)
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle(requireContext().getString(R.string.change_currency))
        val arrayAdapter =
            ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                getSupportedCurrency
            )

        builder.setAdapter(arrayAdapter) { dialog, which ->
            val selectedCurrency = getSupportedCurrency[which]
            screenState.currentExchangeRate?.let {
                changePriceToSelectedCurrency(
                    selectedCurrency, selectedProperty,
                    it
                )
            }
            dialog.dismiss()
        }

        builder.setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun changePriceToSelectedCurrency(
        selectedCurrency: String,
        selectedProperty: Property,
        currencyExchangeRates: CurrencyExchangeRates
    ) {
        currencyExchangeRates.currencyRates?.rates?.let { currencyMap: Map<String, Double?> ->
            if (currencyMap.containsKey(selectedCurrency)) {
                val rate = currencyMap[selectedCurrency]
                rate?.let {
                    val propertyPrice = selectedProperty.lowestPricePerNight?.value?.toDouble()
                   propertyPrice?.let {
                       val realPrice = propertyPrice.times(rate)
                       val decimalFormatter = DecimalFormat("#.##")
                       decimalFormatter.format(realPrice)
                       binding.price.text = String.format(
                           getString(R.string.price),
                           getCurrencySymbolFromCode(selectedCurrency),
                           decimalFormatter.format(realPrice)
                       )
                   }
                }
            }
        }
    }

}
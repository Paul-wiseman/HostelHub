package com.wiseman.hostelworldassessmentapp.presentation.detail.fragment

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.chip.Chip
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.FragmentPropertyDetailBinding
import com.wiseman.hostelworldassessmentapp.domain.model.CurrencyExchangeRates
import com.wiseman.hostelworldassessmentapp.domain.model.Facility
import com.wiseman.hostelworldassessmentapp.domain.model.LowestPricePerNight
import com.wiseman.hostelworldassessmentapp.presentation.adapter.ImageSlideAdapter
import com.wiseman.hostelworldassessmentapp.presentation.home.state.PropertyUiState
import com.wiseman.hostelworldassessmentapp.presentation.home.viewmodel.PropertyListViewModel
import com.wiseman.hostelworldassessmentapp.util.collectInFragment
import com.wiseman.hostelworldassessmentapp.util.formatPrice
import com.wiseman.hostelworldassessmentapp.util.formatRating
import com.wiseman.hostelworldassessmentapp.util.getCurrencySymbolFromCode
import com.wiseman.hostelworldassessmentapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyDetailFragment : Fragment(R.layout.fragment_property_detail) {
    private val propertyListViewModel by activityViewModels<PropertyListViewModel>()
    private val binding by viewBinding(FragmentPropertyDetailBinding::bind)
    private val viewPagerAdapter: ImageSlideAdapter by lazy { ImageSlideAdapter() }
    private val args: PropertyDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        propertyListViewModel.state.collectInFragment { state ->
            state.properties
                ?.find { it.id == args.id }
                ?.let { property ->
                    with(binding) {
                        property.imagesGallery
                            .map { it.imageUrl }
                            .let { list ->
                                viewPagerAdapter.setItemList(list)
                                viewpager.adapter = viewPagerAdapter
                                indicator.setViewPager(viewpager)
                            }
                        propertyName.text = property.name
                        propertyOverview.text = property.overview
                        location.text = String.format(
                            "%s, %s, %s",
                            property.address1,
                            state.location?.name,
                            state.location?.country
                        )

                        price.text = property.lowestPricePerNight?.let { formatPrice(it) }
                        rating.text = formatRating(property.overallRating)
                        pricePerNight.setOnClickListener {
                            property.lowestPricePerNight?.let { price ->
                                showSupportedCurrencyListDialog(state, price)
                            }
                        }
                    }

                    val facilities = property.facilities?.map { facility: Facility ->
                        facility.facilities?.map { it.name } ?: listOf()
                    }?.flatten() ?: listOf()
                    createFacilityList(facilities)

                }
        }
    }

    private fun createFacilityList(list: List<String?>) {
        list.filterNotNull().forEach { facilityName ->
            with(binding) {
                val chipGroup = facilityChipGroup
                val chip = Chip(requireContext())
                chip.text = facilityName
                chip.isClickable = false
                chip.isCloseIconVisible = false
                chipGroup.addView(chip)
            }
        }
    }

    private fun showSupportedCurrencyListDialog(
        screenState: PropertyUiState,
        lowestPricePerNight: LowestPricePerNight
    ) {
        val getSupportedCurrency = resources.getStringArray(R.array.supported_currency)
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(requireContext().getString(R.string.change_currency))
            .setItems(getSupportedCurrency) { dialogInterface, which ->
                val selectedCurrency = getSupportedCurrency[which]
                screenState.currentExchangeRate?.let {
                    changePriceToSelectedCurrency(
                        selectedCurrency, lowestPricePerNight,
                        it
                    )
                }
                dialogInterface.dismiss()
            }
            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun changePriceToSelectedCurrency(
        selectedCurrency: String,
        lowestPricePerNight: LowestPricePerNight,
        currencyExchangeRates: CurrencyExchangeRates
    ) {
        currencyExchangeRates.currencyRates?.rates?.let { currencyMap: Map<String, Double?> ->
            if (currencyMap.containsKey(selectedCurrency)) {
                val rate = currencyMap[selectedCurrency]
                rate?.let {
                    val propertyPrice = lowestPricePerNight.value?.toDouble()
                    propertyPrice?.let {
                        val realPrice = propertyPrice.times(rate)
                        val decimalFormatter = DecimalFormat("#.##")
                        binding.price.text = String.format(
                            "%s%s",
                            getCurrencySymbolFromCode(selectedCurrency),
                            decimalFormatter.format(realPrice)
                        )
                    }
                }
            }
        }
    }

}
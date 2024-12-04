package com.wiseman.hostelworldassessmentapp.presentation.home.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.FragmentHomeBinding
import com.wiseman.hostelworldassessmentapp.domain.model.Property
import com.wiseman.hostelworldassessmentapp.presentation.home.adapter.PropertyListAdapter
import com.wiseman.hostelworldassessmentapp.presentation.home.state.UiState
import com.wiseman.hostelworldassessmentapp.presentation.home.viewmodel.PropertyListViewModel
import com.wiseman.hostelworldassessmentapp.util.ui.CustomProgressDialogFragment
import com.wiseman.hostelworldassessmentapp.util.collectInFragment
import com.wiseman.hostelworldassessmentapp.util.showErrorDialog
import com.wiseman.hostelworldassessmentapp.util.ui.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val adapter: PropertyListAdapter by lazy { PropertyListAdapter() }
    private val propertyListViewModel by activityViewModels<PropertyListViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val customProgressFrag = CustomProgressDialogFragment.newInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getAllAvailableProperties()
        setupItemClickListener()
    }

    private fun setUpRecyclerView() {
        binding.availablePropertiesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = this@HomeFragment.adapter
        }
    }

    private fun setupItemClickListener() {
        adapter.setOnItemClickListener { item: Property ->
            item.id?.let {
                findNavController().navigate(
                    HomeFragmentDirections.actionHomeFragmentToPropertyDetailFragment(
                        item.id,
                        label = item.name ?: ""
                    )
                )
            }
        }
    }

    private fun getAllAvailableProperties() {
        propertyListViewModel.state.collectInFragment { homeScreenViewState ->

            when (homeScreenViewState.state) {
                UiState.Error -> {
                    showCustomProgressSheet(requireActivity(), false)
                    val errorMassage = homeScreenViewState.error
                        ?: getString(R.string.error_fetching_available_properties)
                    showErrorDialog(
                        requireContext(), getString(R.string.something_went_wrong),
                        errorMassage
                    )
                }

                UiState.Loading -> {
                    showCustomProgressSheet(requireActivity(), true)
                }

                UiState.Success -> {
                    showCustomProgressSheet(requireActivity(), false)
                    homeScreenViewState.properties?.let { adapter.setItemList(it) }
                }
            }
        }
    }

    private fun showCustomProgressSheet(
        activity: FragmentActivity,
        show: Boolean
    ) {
        if (!activity.isFinishing) {
            if (show) {
                customProgressFrag.show(
                    activity.supportFragmentManager,
                    TAG
                )
            } else {
                customProgressFrag.dismiss()
            }
        }
    }

    private companion object {
        const val TAG = "HomeFragment"
    }
}
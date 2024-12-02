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
import com.wiseman.hostelworldassessmentapp.presentation.home.UiState
import com.wiseman.hostelworldassessmentapp.presentation.viewmodel.PropertyListViewModel
import com.wiseman.hostelworldassessmentapp.util.CustomProgressDialogFragment
import com.wiseman.hostelworldassessmentapp.util.collectInFragment
import com.wiseman.hostelworldassessmentapp.util.showErrorDialog
import com.wiseman.hostelworldassessmentapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val propertyListAdapter: PropertyListAdapter by lazy { PropertyListAdapter() }
    private val propertyListViewModel by activityViewModels<PropertyListViewModel>()
    private val binding by viewBinding(FragmentHomeBinding::bind)

    private val customProgressFrag = CustomProgressDialogFragment.newInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerView()
        getAllAvailableProperties()
        handleClickItemClicked()
    }

    private fun setUpRecyclerView() {
        binding.availablePropertiesRv.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = propertyListAdapter
            propertyListAdapter
        }
    }

    private fun handleClickItemClicked() {
        propertyListAdapter.setOnclickListener { item: Property ->
            item.id?.let {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToPropertyDetailFragment(item.id, label = item.name?:""))
            }
        }
    }

    private fun getAllAvailableProperties() {
        propertyListViewModel.state.collectInFragment { homeScreenViewState ->

            when (homeScreenViewState.state) {
                UiState.Error -> {
                    showCustomProgressSheet(requireActivity(), false)
                    homeScreenViewState.error?.let {
                        showErrorDialog(requireContext(), "something went wrong",
                            it
                        )
                    }
                }

                UiState.Loading -> {
                    showCustomProgressSheet(requireActivity(), true)
                }

                UiState.Success -> {
                    showCustomProgressSheet(requireActivity(), false)
                    homeScreenViewState.properties?.let { propertyListAdapter.setItemList(it) }
                }
            }
        }
    }

    private fun showCustomProgressSheet(
        activity: FragmentActivity,
        show: Boolean
    ) {
        if (!activity.isFinishing && show) {
            customProgressFrag.show(
                activity.supportFragmentManager,
                "CustomProgressDialogFragment"
            )
        } else if (!activity.isFinishing && !show) {
            customProgressFrag.dismiss()
        }
    }
}
package com.wiseman.hostelworldassessmentapp.presentation.home.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.filters.MediumTest
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.presentation.home.adapter.PropertyListAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import launchFragmentInHiltContainer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import source.TestDataFactory

@MediumTest
@OptIn(ExperimentalCoroutinesApi::class)
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun testHomeFragment() {
        launchFragmentInHiltContainer<HomeFragment>()
        onView(ViewMatchers.withId(R.id.available_properties_rv))
            .perform(RecyclerViewActions.scrollToPosition<PropertyListAdapter.PropertyViewHolder>(0))
            .check(
                ViewAssertions.matches(
                    ViewMatchers.hasDescendant(
                        ViewMatchers.withText(
                            TestDataFactory.getAvailablePropertiesDto().properties?.get(0)?.name
                        )
                    )
                )
            )
    }
}
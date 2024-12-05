package com.wiseman.hostelworldassessmentapp.presentation.home.fragment

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
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

        launchFragmentInHiltContainer<HomeFragment> {

        }

        onView(ViewMatchers.withId(R.id.available_properties_rv))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<PropertyListAdapter.PropertyViewHolder>(
                    1,
                    click()
                )
            )

    }

}
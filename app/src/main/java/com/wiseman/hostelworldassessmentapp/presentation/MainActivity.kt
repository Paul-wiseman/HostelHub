package com.wiseman.hostelworldassessmentapp.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.wiseman.hostelworldassessmentapp.R
import com.wiseman.hostelworldassessmentapp.databinding.ActivityMainBinding
import com.wiseman.hostelworldassessmentapp.presentation.home.viewmodel.PropertyListViewModel
import com.wiseman.hostelworldassessmentapp.util.collectInActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var navHostFragment: NavHostFragment
    private val propertyListViewModel: PropertyListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpNavigation()
        changeToolbarTitle()
    }

    private fun changeToolbarTitle() {
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.id == R.id.homeFragment) {
                supportActionBar?.setDisplayShowTitleEnabled(true)
                propertyListViewModel.state.collectInActivity { state ->
                    supportActionBar?.title = state.location?.name
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setUpNavigation() {
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment

        setSupportActionBar(binding.toolbar)

        navController = navHostFragment.findNavController().run {
            findViewById<Toolbar>(R.id.toolbar)
                .setupWithNavController(this)
            this
        }
    }
}
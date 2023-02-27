package com.edurda77.shoptest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.shoptest.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navView: BottomNavigationView

    @Inject
    lateinit var coordinator: AppNavigation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        navView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_favorite,
                R.id.navigation_basket,
                R.id.navigation_note,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        coordinator.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.navigation_home -> {
                    showBottomNav()
                }
                R.id.navigation_favorite -> {
                    showBottomNav()
                }
                R.id.navigation_basket -> {
                    showBottomNav()
                }
                R.id.navigation_note -> {
                    showBottomNav()
                }
                R.id.navigation_profile -> {
                    showBottomNav()
                }
                else -> {
                    hideBottomNav()
                }
            }
        }
    }
    private fun showBottomNav() {
        navView.visibility = View.VISIBLE
    }

    private fun hideBottomNav() {
        navView.visibility = View.GONE
    }
}
package com.edurda77.shoptest.navigation

import android.app.Activity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.shoptest.R
import javax.inject.Inject

class AppNavigationImpl @Inject constructor(
    activity: Activity
) : AppNavigation {

    override val navController: NavController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main)
    }

    override fun execute(
        action: Action,
        sec_id: String,
        changePrice: Double,
        changePercent: Double
    ) {
        when (action) {
            Action.SignInToLogIn -> {
                navController.navigate(R.id.logIn_fragment)
            }
            Action.SignInToHome -> {
                navController.navigate(R.id.navigation_home)
            }
            Action.LogInToHome -> {
                navController.navigate(R.id.navigation_home)
            }
            Action.ProfileToSignIn -> {
                navController.navigate(R.id.signIn_fragment)
            }
            Action.HomeToProduct -> {
                navController.navigate(R.id.product_fragment)
            }
        }
    }
}
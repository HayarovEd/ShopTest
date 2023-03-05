package com.edurda77.shoptest.navigation

import android.app.Activity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.edurda77.domain.navigation.Action
import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.domain.utils.USERNAME
import com.edurda77.shoptest.R
import javax.inject.Inject

class AppNavigationImpl @Inject constructor(
    activity: Activity
) : AppNavigation {

    override val navController: NavController by lazy {
        Navigation.findNavController(activity, R.id.nav_host_fragment_activity_main)
    }

    override fun execute(
        action: Action, user:String
    ) {
        when (action) {
            Action.SignInToLogIn -> {
                navController.navigate(R.id.logIn_fragment)
            }
            Action.SignInToHome -> {
                val bundle = Bundle()
                bundle.putString(USERNAME, user)
                navController.navigate(R.id.navigation_home, bundle)
            }
            Action.LogInToHome -> {
                val bundle = Bundle()
                bundle.putString(USERNAME, user)
                navController.navigate(R.id.navigation_home, bundle)
            }
            Action.ProfileToSignIn -> {
                navController.navigate(R.id.signIn_fragment)
            }
            Action.HomeToProduct -> {
                navController.navigate(R.id.product_fragment)
            }
            Action.ProductToHome -> {
                navController.navigate(R.id.navigation_home)
            }
        }
    }
}


package com.edurda77.domain.navigation

import androidx.navigation.NavController

interface AppNavigation {
    fun execute(action: Action, user:String)
    val navController: NavController
}
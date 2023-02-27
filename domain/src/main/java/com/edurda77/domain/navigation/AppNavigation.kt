package com.edurda77.domain.navigation

import androidx.navigation.NavController

interface AppNavigation {
    fun execute(action: Action, sec_id: String, changePrice: Double, changePercent: Double)
    val navController: NavController
}
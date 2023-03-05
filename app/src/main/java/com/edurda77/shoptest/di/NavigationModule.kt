package com.edurda77.shoptest.di

import com.edurda77.domain.navigation.AppNavigation
import com.edurda77.shoptest.navigation.AppNavigationImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface NavigationModule {

    @Binds
    fun bindNavController(appNavigationImpl: AppNavigationImpl): AppNavigation

}
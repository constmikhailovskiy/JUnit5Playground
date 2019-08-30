package com.betterme.junitplayground.navigation

interface AppNavigator {

    fun navigateToOnboarding()

    fun navigateToDashboard()

    fun navigateToSubscriptionScreen()

    fun navigateToSubscriptionExpiredScreen()

    fun navigateToExclusivePaidContentScreen(skuItemId: String)

    fun navigateToEasyTrivia()

    fun navigateToHardTrivia()
}

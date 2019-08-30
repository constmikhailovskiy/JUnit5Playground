package com.betterme.junitplayground.navigation

import com.betterme.junitplayground.models.PurchaseState
import com.betterme.junitplayground.models.TriviaLevel

class AppCoordinator(
    private val navigator: AppNavigator
) {

    fun showFirstScreen(userAuthorized: Boolean) {
        if (userAuthorized) {
            navigator.navigateToDashboard()
        } else {
            navigator.navigateToOnboarding()
        }
    }

    fun accessPaidContent(purchaseState: PurchaseState) {
        with(navigator) {
            when (purchaseState) {
                PurchaseState.SubscriptionExpired -> navigateToSubscriptionExpiredScreen()
                PurchaseState.NoSubscription -> navigateToSubscriptionScreen()
                is PurchaseState.SubscriptionAvailable -> {
                    navigateToExclusivePaidContentScreen(purchaseState.skuId)
                }
            }
        }
    }

    fun openTriviaGame(triviaLevel: TriviaLevel) {
        when (triviaLevel) {
            TriviaLevel.NEWBIE, TriviaLevel.MEDIUM -> {
                navigator.navigateToEasyTrivia()
            }
            TriviaLevel.ADVANCED -> {
                navigator.navigateToHardTrivia()
            }
        }
    }

    fun openPaidTriviaGame(purchaseState: PurchaseState, triviaLevel: TriviaLevel) {
        when (purchaseState) {
            PurchaseState.NoSubscription, PurchaseState.SubscriptionExpired -> {
                navigator.navigateToSubscriptionScreen()
            }
            is PurchaseState.SubscriptionAvailable -> {
                when (triviaLevel) {
                    TriviaLevel.NEWBIE, TriviaLevel.MEDIUM -> {
                        navigator.navigateToEasyTrivia()
                    }
                    TriviaLevel.ADVANCED -> {
                        navigator.navigateToHardTrivia()
                    }
                }
            }
        }
    }
}
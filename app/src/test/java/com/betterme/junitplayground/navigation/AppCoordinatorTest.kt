package com.betterme.junitplayground.navigation

import com.betterme.junitplayground.models.PurchaseState
import com.betterme.junitplayground.models.TriviaLevel
import com.betterme.junitplayground.utils.converters.BooleanArgumentConverter
import com.betterme.junitplayground.utils.converters.PurchaseStateArgumentConverter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.jupiter.api.Tag
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.ConvertWith
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.EnumSource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource

private const val TAG_PAID_UNPAID_LOGIC = "paid_unpaid_logic"

class AppCoordinatorTest {

    companion object {

        @JvmStatic
        fun createPaidTriviaGameArgs() = listOf(
            // purchaseState: PurchaseState, triviaLevel: TriviaLevel
            Arguments.of("SubscriptionAvailable", TriviaLevel.ADVANCED),
            Arguments.of("SubscriptionAvailable", TriviaLevel.MEDIUM),
            Arguments.of("SubscriptionAvailable", TriviaLevel.NEWBIE),
            Arguments.of("NoSubscription", TriviaLevel.ADVANCED),
            Arguments.of("NoSubscription", TriviaLevel.MEDIUM),
            Arguments.of("NoSubscription", TriviaLevel.NEWBIE),
            Arguments.of("SubscriptionExpired", TriviaLevel.ADVANCED),
            Arguments.of("SubscriptionExpired", TriviaLevel.MEDIUM),
            Arguments.of("SubscriptionExpired", TriviaLevel.NEWBIE)
        )
    }

    private val navigator: AppNavigator = mock()
    private val coordinator = AppCoordinator(navigator)

    @ParameterizedTest(name = "show the first screen in navigation flow = {0}")
    @ValueSource(strings = ["true", "false"])
    fun `show the first screen`(@ConvertWith(BooleanArgumentConverter::class) userAuthorized: Boolean) {
        coordinator.showFirstScreen(userAuthorized)

        with(verify(navigator)) {
            if (userAuthorized) {
                navigateToDashboard()
            } else {
                navigateToOnboarding()
            }
        }
        verifyNoMoreInteractions(navigator)
    }

    @Tag(TAG_PAID_UNPAID_LOGIC)
    @ParameterizedTest(name = "try to access paid content with {0}")
    @ValueSource(strings = ["SubscriptionAvailable", "SubscriptionExpired", "NoSubscription"])
    fun `try to access paid content`(
        @ConvertWith(PurchaseStateArgumentConverter::class) purchaseState: PurchaseState
    ) {
        coordinator.accessPaidContent(purchaseState)

        with(verify(navigator)) {
            when (purchaseState) {
                PurchaseState.SubscriptionExpired -> {
                    navigateToSubscriptionExpiredScreen()
                }
                PurchaseState.NoSubscription -> {
                    navigateToSubscriptionScreen()
                }
                is PurchaseState.SubscriptionAvailable -> {
                    navigateToExclusivePaidContentScreen(purchaseState.skuId)
                }
            }
        }
        verifyNoMoreInteractions(navigator)
    }

    @ParameterizedTest(name = "open trivia game for the user with {0} difficulty level")
    @EnumSource(TriviaLevel::class)
    fun `open trivia game for the user`(triviaLevel: TriviaLevel) {
        coordinator.openTriviaGame(triviaLevel)

        when (triviaLevel) {
            TriviaLevel.NEWBIE, TriviaLevel.MEDIUM -> {
                verify(navigator).navigateToEasyTrivia()
            }
            TriviaLevel.ADVANCED -> {
                verify(navigator).navigateToHardTrivia()
            }
        }
        verifyNoMoreInteractions(navigator)
    }

    @Tag(TAG_PAID_UNPAID_LOGIC)
    @ParameterizedTest(name = "open trivia for the user with {0} subscription state and {1} desired trivia level")
    @MethodSource("createPaidTriviaGameArgs")
    fun `open trivia fame for the user with a certain subscription state`(
        @ConvertWith(PurchaseStateArgumentConverter::class) purchaseState: PurchaseState,
        triviaLevel: TriviaLevel
    ) {
        coordinator.openPaidTriviaGame(purchaseState, triviaLevel)

        with(verify(navigator)) {
            when (purchaseState) {
                PurchaseState.NoSubscription, PurchaseState.SubscriptionExpired -> {
                    navigateToSubscriptionScreen()
                }
                is PurchaseState.SubscriptionAvailable -> {
                    when (triviaLevel) {
                        TriviaLevel.NEWBIE, TriviaLevel.MEDIUM -> {
                            navigateToEasyTrivia()
                        }
                        TriviaLevel.ADVANCED -> {
                            navigateToHardTrivia()
                        }
                    }
                }
            }
        }
        verifyNoMoreInteractions(navigator)
    }
}

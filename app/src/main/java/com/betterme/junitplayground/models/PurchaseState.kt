package com.betterme.junitplayground.models

sealed class PurchaseState {

    object NoSubscription : PurchaseState()

    data class SubscriptionAvailable(val skuId: String) : PurchaseState()

    object SubscriptionExpired : PurchaseState()
}

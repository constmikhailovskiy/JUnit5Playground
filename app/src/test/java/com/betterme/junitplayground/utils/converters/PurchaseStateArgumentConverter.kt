package com.betterme.junitplayground.utils.converters

import com.betterme.junitplayground.models.PurchaseState
import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConverter
import java.lang.IllegalStateException

class PurchaseStateArgumentConverter : ArgumentConverter {

    override fun convert(source: Any?, context: ParameterContext?): Any {
        return when (source) {
            is PurchaseState -> source
            is String -> when (source) {
                "NoSubscription" -> PurchaseState.NoSubscription
                "SubscriptionAvailable" -> PurchaseState.SubscriptionAvailable(skuId = "lifetime_subscription_666")
                "SubscriptionExpired" -> PurchaseState.SubscriptionExpired
                else -> throw IllegalStateException("Unsupported purchase state: $source")
            }
            else -> throw IllegalStateException("Unsupported type")
        }
    }
}
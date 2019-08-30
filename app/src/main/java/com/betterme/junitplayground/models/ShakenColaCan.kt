package com.betterme.junitplayground.models

class ShakenColaCan(
    private val detonator: ColaCanDetonator
) {

    fun boom(needsToExplode: Boolean) {
        check(!needsToExplode) { "Boom!" } // will throw IllegalStateException
        detonator.updateDetonatorRemainingTime()
    }
}

class ColaCanDetonator {

    private var timeLeftToExplosion = 60

    fun updateDetonatorRemainingTime() {
        timeLeftToExplosion--
    }
}
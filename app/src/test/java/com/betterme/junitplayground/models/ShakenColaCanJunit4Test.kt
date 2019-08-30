package com.betterme.junitplayground.models

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.Ignore
import org.junit.Test

class ShakenColaCanJunit4Test {

    private val detonator: ColaCanDetonator = mock()
    private val shakenColaCan = ShakenColaCan(detonator)

    @Test(expected = IllegalStateException::class)
    fun `boom while needs to explode`() {
        shakenColaCan.boom(needsToExplode = true)

        verifyZeroInteractions(detonator)
    }

    @Test
    @Ignore(value = "because")
    fun `boom while there is some time left before the explosion`() {
        shakenColaCan.boom(needsToExplode = false)

        verify(detonator).updateDetonatorRemainingTime()
        verifyNoMoreInteractions(detonator)
    }
}
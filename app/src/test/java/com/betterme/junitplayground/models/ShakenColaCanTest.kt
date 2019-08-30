package com.betterme.junitplayground.models

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.verifyZeroInteractions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.Execution
import org.junit.jupiter.api.parallel.ExecutionMode

@Execution(ExecutionMode.SAME_THREAD)
class ShakenColaCanTest {

    private val detonator: ColaCanDetonator = mock()
    private val shakenColaCan = ShakenColaCan(detonator)

    @Test
    fun `boom while needs to explode`() {
        assertThrows<IllegalStateException>("Boom!") {
            shakenColaCan.boom(needsToExplode = true)
        }

        verifyZeroInteractions(detonator)
    }

    @Test
    @Disabled(value = "for no reasons")
    fun `boom while there is some time left before the explosion`() {
        shakenColaCan.boom(needsToExplode = false)

        verify(detonator).updateDetonatorRemainingTime()
        verifyNoMoreInteractions(detonator)
    }
}
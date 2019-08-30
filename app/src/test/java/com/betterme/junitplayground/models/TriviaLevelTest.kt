package com.betterme.junitplayground.models

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class TriviaLevelTest {

    @ParameterizedTest(name = "make sure {0} can be mapped to an appropriate trivia level")
    @ValueSource(doubles = [0.0, 0.21, 0.43, 0.55, 0.66, 0.69, 0.79, 0.8, 0.99, 1.2, 1000.0])
    fun `make sure the double value is mapped to an appropriate trivia level`(percentageValue: Double) {
        val actualTriviaLevel = lazy { percentageValue.toTriviaLevel() }

        when (percentageValue) {
            in 0.0..0.33 -> assertThat(actualTriviaLevel.value).isEqualTo(TriviaLevel.NEWBIE)
            in 0.33..0.66 -> assertThat(actualTriviaLevel.value).isEqualTo(TriviaLevel.MEDIUM)
            in 0.66..1.0 -> assertThat(actualTriviaLevel.value).isEqualTo(TriviaLevel.ADVANCED)
            else -> assertThrows<IllegalArgumentException> { actualTriviaLevel.value }
        }
    }
}

package com.betterme.junitplayground.models.mappers

import com.betterme.junitplayground.models.Quiz
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.RepetitionInfo
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class QuizMapperTest {

    private val quizTitles = listOf("Movie quiz", "Books quiz", "Music quiz",
        "Kotlin programming language quiz", "Geography quiz")
    private val mapper = QuizMapper()

    @RepeatedTest(value = 5, name = "create quiz instance by its position " +
        "#{currentRepetition} (of {totalRepetitions}) and title")
    fun createQuizFromTitle(repetitionInfo: RepetitionInfo) {
        val currentRepetition = repetitionInfo.currentRepetition

        assertEquals(
            mapper.createQuizFromTitle(currentRepetition, quizTitles[currentRepetition.dec()]),
            Quiz(currentRepetition, quizTitles[currentRepetition.dec()])
        ) {
            "Created the appropriate quiz model #${currentRepetition} by parameters"
        }
    }
}
package com.betterme.junitplayground

import com.betterme.junitplayground.models.Quiz
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll


class AssertionsDemoTest {

    @Test
    fun `assertions demo test`() {
        assertTrue(20 * 5 == 50 * 2) { "Lazy evaluated assertion description" }

        val anotherList = listOf(4, 3, 5, 6)
        assertIterableEquals(listOf(4, 3, 5, 6), anotherList)

        assertLinesMatch(
            listOf("first line", "current date is \\d{2}\\.\\d{2}\\.\\d{4}"),
            listOf("first line", "current date is 31.08.2019")
        )

        val quiz = Quiz(id = 42, title = "Movie quiz")
        assertAll(
            { quiz.id == 42 },
            { quiz.title == "Movie quiz" }
        )
    }
}
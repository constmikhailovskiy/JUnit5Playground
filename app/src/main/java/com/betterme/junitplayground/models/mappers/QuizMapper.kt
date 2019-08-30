package com.betterme.junitplayground.models.mappers

import com.betterme.junitplayground.models.Quiz

class QuizMapper {

    fun createQuizFromTitle(id: Int, title: String) = Quiz(id, title)
}
package com.betterme.junitplayground.models

enum class TriviaLevel {
    NEWBIE, MEDIUM, ADVANCED
}

fun Double.toTriviaLevel(): TriviaLevel {
    return when(this) {
        in 0.0..0.33 -> TriviaLevel.NEWBIE
        in 0.33..0.66 -> TriviaLevel.MEDIUM
        in 0.66..1.0 -> TriviaLevel.ADVANCED
        else -> throw IllegalArgumentException("Failed to convert percent value: $this to trivia level!")
    }
}
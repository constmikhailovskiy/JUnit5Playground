package com.betterme.junitplayground.utils.converters

import org.junit.jupiter.api.extension.ParameterContext
import org.junit.jupiter.params.converter.ArgumentConverter

class BooleanArgumentConverter : ArgumentConverter {

    override fun convert(source: Any?, context: ParameterContext?): Any {
        return when (source) {
            is Boolean -> source
            is String -> source.toBoolean()
            else -> throw IllegalStateException("Invalid type used")
        }
    }
}
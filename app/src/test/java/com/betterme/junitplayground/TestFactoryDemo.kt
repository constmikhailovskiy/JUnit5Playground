package com.betterme.junitplayground

import org.junit.jupiter.api.*
import java.util.stream.Stream

class TestFactoryDemo {

    @TestFactory
    fun dynamicTestsWithCollection(): Collection<DynamicTest> {
        return listOf(
            DynamicTest.dynamicTest("Add test") {
                Assertions.assertEquals(2, Math.addExact(1, 1))
            },
            DynamicTest.dynamicTest("Multiply Test") {
                Assertions.assertEquals(4, Math.multiplyExact(2, 2))
            })
    }

    @TestFactory
    fun dynamicTestsWithContainers(): Stream<DynamicNode> {
        return Stream.of("A", "B", "C")
            .map { input ->
                DynamicContainer.dynamicContainer("Container $input", Stream.of(
                    DynamicTest.dynamicTest("not null") { Assertions.assertNotNull(input) },
                    DynamicContainer.dynamicContainer("properties", Stream.of(
                        DynamicTest.dynamicTest("length > 0") { Assertions.assertTrue(input.length > 0) },
                        DynamicTest.dynamicTest("not empty") { Assertions.assertFalse(input.isEmpty()) }
                    ))
                ))
            }
    }
}
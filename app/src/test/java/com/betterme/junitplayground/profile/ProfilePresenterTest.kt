package com.betterme.junitplayground.profile

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class ProfilePresenterTest {

    private val analytics: ProfileAnalytics = mock()
    private val presenter = ProfilePresenter(analytics)

    @Test
    @Order(1)
    fun `set username`() {
        val name = "Jake"
        presenter.saveName(name)

        assertThat(presenter.userProfile.name).isEqualTo(name)
    }

    @Test
    @Order(2)
    fun `set age`() {
        val age = 42
        presenter.saveAge(age)

        assertThat(presenter.userProfile.age).isEqualTo(age)
    }

    @Test
    @Order(3)
    fun `send analytics event`() {
        presenter.trackUserParametersToAnalytics()

        verify(analytics).trackUserName("Jake")
        verify(analytics).trackUserAge(42)
        verifyNoMoreInteractions(analytics)
    }
}
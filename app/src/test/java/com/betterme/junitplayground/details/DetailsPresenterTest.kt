package com.betterme.junitplayground.details

import com.betterme.domain.Details
import com.betterme.domain.GetDetailsRequest
import com.betterme.domain.GetDetailsUseCase
import com.betterme.junitplayground.SynchronousSchedulersExtension
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class, SynchronousSchedulersExtension::class)
class DetailsPresenterTest {

    private val getDetailsUseCase: GetDetailsUseCase = mock()
    private val view: DetailsView = mock()
    private val presenter = DetailsPresenter(getDetailsUseCase)

    @BeforeEach
    fun setUp() {
        presenter.bindView(view)
    }

    @AfterEach
    fun tearDown() {
        presenter.unbindView()
    }

    @Test
    fun `load details successfully`() {
        val request = GetDetailsRequest(id = 42)
        val details = Details()
        whenever(getDetailsUseCase.get(request)).thenReturn(Single.just(details))

        presenter.loadDetails()

        inOrder(getDetailsUseCase, view) {
            verify(getDetailsUseCase).get(request)
            verify(view).updateDetailsState(DetailsState.Loading)
            verify(view).updateDetailsState(DetailsState.Loaded(details))
            verifyNoMoreInteractions()
        }
    }

    @Test
    fun `load details with failure`() {
        val request = GetDetailsRequest(id = 42)
        whenever(getDetailsUseCase.get(request)).thenReturn(Single.error(
            Throwable("Could not load details :(")))

        presenter.loadDetails()

        inOrder(getDetailsUseCase, view) {
            verify(getDetailsUseCase).get(request)
            verify(view).updateDetailsState(DetailsState.Loading)
            verify(view).updateDetailsState(DetailsState.Error)
            verifyNoMoreInteractions()
        }
    }
}

package com.betterme.junitplayground.details

import com.betterme.domain.Details
import com.betterme.domain.GetDetailsRequest
import com.betterme.domain.GetDetailsUseCase
import com.betterme.junitplayground.utils.SynchronousSchedulersRx
import com.nhaarman.mockitokotlin2.inOrder
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailsPresenterJunit4Test {

    private val getDetailsUseCase: GetDetailsUseCase = mock()
    private val view: DetailsView = mock()
    private val presenter = DetailsPresenter(getDetailsUseCase)

    @Rule
    @JvmField
    val rules = SynchronousSchedulersRx()

    @Before
    fun setUp() {
        presenter.bindView(view)
    }

    @After
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

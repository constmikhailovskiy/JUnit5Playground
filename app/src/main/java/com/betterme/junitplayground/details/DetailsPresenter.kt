package com.betterme.junitplayground.details

import com.betterme.domain.base.AppSchedulers
import com.betterme.domain.GetDetailsRequest
import com.betterme.domain.GetDetailsUseCase
import io.reactivex.disposables.CompositeDisposable

class DetailsPresenter(
    private val getDetailsUseCase: GetDetailsUseCase
) {

    private val disposables = CompositeDisposable()
    private var detailsView: DetailsView? = null

    fun bindView(view: DetailsView) {
        detailsView = view
    }

    fun unbindView() {
        detailsView = null
    }

    fun loadDetails() {
        disposables.add(
            getDetailsUseCase.get(request = GetDetailsRequest(id = 42))
                .doOnSubscribe { detailsView?.updateDetailsState(detailsState = DetailsState.Loading) }
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
                .subscribe({
                    detailsView?.updateDetailsState(DetailsState.Loaded(it))
                }, {
                    detailsView?.updateDetailsState(DetailsState.Error)
                })
        )
    }

    fun addQuizToFavorites(quizId: Int) {

    }
}
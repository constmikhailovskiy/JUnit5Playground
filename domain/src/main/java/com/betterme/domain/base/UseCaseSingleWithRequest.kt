package com.betterme.domain.base

import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

abstract class UseCaseSingleWithRequest<Result, Request : Any> {

    private var disposable = CompositeDisposable()
    protected lateinit var request: Request

    protected abstract fun buildUseCaseSingle(): Single<Result>

    fun get(request: Request): Single<Result> {
        this.request = request
        return this.buildUseCaseSingle()
                .subscribeOn(AppSchedulers.io())
                .observeOn(AppSchedulers.mainThread())
    }

    fun execute(request: Request, observer: DisposableSingleObserver<Result>) {
        disposable.add(get(request)
                .subscribeWith(observer))
    }

    /**
     * Unsubscribes from current [Disposables].
     */
    fun dispose() {
        disposable.clear()
    }
}

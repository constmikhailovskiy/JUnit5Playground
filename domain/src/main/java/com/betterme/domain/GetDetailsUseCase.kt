package com.betterme.domain

import com.betterme.domain.base.UseCaseSingleWithRequest
import io.reactivex.Single

class GetDetailsUseCase : UseCaseSingleWithRequest<Details, GetDetailsRequest>() {

    override fun buildUseCaseSingle(): Single<Details> {
        return Single.just(Details())
    }
}

data class Details(
    val id: Int = 42,
    val title: String = "Isle of Dogs",
    val description: String = "One of the latest Wes Anderson's masterpieces"
)

data class GetDetailsRequest(val id: Int)
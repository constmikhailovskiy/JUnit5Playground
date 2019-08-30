package com.betterme.junitplayground.details

interface DetailsView {

    fun updateDetailsState(detailsState: DetailsState)

    fun updateDetonatorTime(timeLeft: Int)
}
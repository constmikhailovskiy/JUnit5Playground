package com.betterme.junitplayground.details

import com.betterme.domain.Details

sealed class DetailsState {

    object Loading : DetailsState()

    data class Loaded(val details: Details) : DetailsState()

    object Error : DetailsState()
}
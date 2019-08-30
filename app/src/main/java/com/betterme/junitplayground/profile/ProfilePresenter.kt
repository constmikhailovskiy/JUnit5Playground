package com.betterme.junitplayground.profile

import com.betterme.junitplayground.models.UserProfile

class ProfilePresenter(
    private val analytics: ProfileAnalytics
) {

    var userProfile = UserProfile()

    fun saveAge(age: Int) {
        userProfile = userProfile.copy(age = age)
    }

    fun saveName(name: String) {
        userProfile = userProfile.copy(name = name)
    }

    fun trackUserParametersToAnalytics() {
        with(analytics) {
            userProfile.name?.let { trackUserName(it) }
            userProfile.age?.let { trackUserAge(it) }
        }
    }
}

interface ProfileAnalytics {

    fun trackUserName(name: String)

    fun trackUserAge(age: Int)
}
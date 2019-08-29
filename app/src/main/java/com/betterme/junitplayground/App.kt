package com.betterme.junitplayground

import android.app.Application
import com.betterme.domain.AppSchedulers

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppSchedulers.setInstance(DefaultSchedulerProvider())
    }
}
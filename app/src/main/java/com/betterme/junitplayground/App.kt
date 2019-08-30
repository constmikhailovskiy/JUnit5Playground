package com.betterme.junitplayground

import android.app.Application
import com.betterme.domain.base.AppSchedulers

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        AppSchedulers.setInstance(DefaultSchedulerProvider())
    }
}
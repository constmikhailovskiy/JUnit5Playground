package com.betterme.junitplayground

import com.betterme.domain.AppSchedulers
import com.betterme.domain.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class SynchronousSchedulersExtension : BeforeAllCallback {

    override fun beforeAll(context: ExtensionContext?) {
        AppSchedulers.setInstance(object : SchedulerProvider {

            override fun mainThread(): Scheduler = Schedulers.trampoline()

            override fun io(): Scheduler = Schedulers.trampoline()

            override fun computation(): Scheduler = Schedulers.trampoline()

            override fun newThread(): Scheduler = Schedulers.trampoline()

            override fun trampoline(): Scheduler = Schedulers.trampoline()
        })

    }
}

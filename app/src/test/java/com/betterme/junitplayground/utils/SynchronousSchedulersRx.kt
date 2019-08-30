package com.betterme.junitplayground.utils

import com.betterme.domain.AppSchedulers
import com.betterme.domain.SchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.junit.rules.ExternalResource

class SynchronousSchedulersRx : ExternalResource() {

    @Throws(Throwable::class)
    public override fun before() {
        AppSchedulers.setInstance(object : SchedulerProvider {
            override fun mainThread(): Scheduler = Schedulers.trampoline()

            override fun io(): Scheduler = Schedulers.trampoline()

            override fun computation(): Scheduler = Schedulers.trampoline()

            override fun newThread(): Scheduler = Schedulers.trampoline()

            override fun trampoline(): Scheduler = Schedulers.trampoline()
        })
    }
}
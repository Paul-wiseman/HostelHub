package com.wiseman.hostelworldassessmentapp.util

import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers

val testScheduler = object : SchedulerProvider {
    override fun computation(): Scheduler =
        Schedulers.trampoline()


    override fun io(): Scheduler = Schedulers.trampoline()

    override fun ui(): Scheduler = Schedulers.trampoline()

}
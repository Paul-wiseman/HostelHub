package com.wiseman.hostelworldassessmentapp.util.rx

import io.reactivex.rxjava3.core.Scheduler


/**
 * Provides schedulers for asynchronous operations.
 *
 * This interface defines methods for obtaining different types
 * of schedulers used for executing asynchronous tasks in RxJava.
 * Implementations of this interface can provide different schedulers based
 * on the platform or environment.
 *
 * This interface is implemented by [SchedulerProviderImpl] and injected
 * with hilt for classes that needs it
 */
interface SchedulerProvider {
    fun computation(): Scheduler

    fun io(): Scheduler

    fun ui(): Scheduler
}
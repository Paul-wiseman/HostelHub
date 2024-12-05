package com.wiseman.hostelworldassessmentapp.di

import com.wiseman.hostelworldassessmentapp.data.FakeRepository
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import testScheduler
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class, AppModule::class]
)
object FakeRepositoryModule {


    @Singleton
    @Provides
    fun providesFakePropertyListRepository(): AvailablePropertiesRepository{
        return FakeRepository()
    }

    @Singleton
    @Provides
    fun providesTestScheduler():SchedulerProvider = testScheduler
}
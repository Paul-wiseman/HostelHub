package com.wiseman.hostelworldassessmentapp.di

import android.content.Context
import com.wiseman.hostelworldassessmentapp.data.repository.AvailablePropertiesRepositoryImpl
import com.wiseman.hostelworldassessmentapp.data.source.remote.HostelApiService
import com.wiseman.hostelworldassessmentapp.domain.repository.AvailablePropertiesRepository
import com.wiseman.hostelworldassessmentapp.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideHostelApiService(
        retrofit: Retrofit
    ): HostelApiService = retrofit.create()

    @Singleton
    @Provides
    fun provideAvailablePropertiesRepository(
        hostelApiService: HostelApiService,
        @ApplicationContext context: Context,
        networkUtil: NetworkUtil
    ): AvailablePropertiesRepository = AvailablePropertiesRepositoryImpl(
        hostelApiService = hostelApiService,
        context = context,
        networkUtil = networkUtil
    )
}
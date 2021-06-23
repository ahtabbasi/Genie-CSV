package com.abbasi.csvreader.commons.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatcherModule {

    @Provides
    @Singleton
    fun CoroutineDispatcher() :CoroutineDispatcher = Dispatchers.IO
}
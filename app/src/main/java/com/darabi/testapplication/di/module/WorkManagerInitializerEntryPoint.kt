package com.darabi.testapplication.di.module

import androidx.hilt.work.HiltWorkerFactory
import com.darabi.testapplication.util.factory.InjectingWorkerFactory
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface WorkManagerInitializerEntryPoint {

    fun hiltWorkerFactory(): InjectingWorkerFactory
}
package com.darabi.testapplication.di.module

import com.darabi.testapplication.cache.Cache
import com.darabi.testapplication.cache.CacheImpl
import com.darabi.testapplication.cache.database.DataBase
import com.darabi.testapplication.cache.database.DatabaseImpl
import com.darabi.testapplication.ui.home.worker.WorkerHandler
import com.darabi.testapplication.ui.home.worker.WorkerHandlerImpl
import com.google.gson.Gson
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: CacheImpl): Cache

    @Binds
    abstract fun bindDatabase(dataBase: DatabaseImpl): DataBase

    @Binds
    abstract fun bindWorkerHandler(handler: WorkerHandlerImpl): WorkerHandler

    companion object {

        @Provides
        fun provideGson(): Gson = Gson()
    }
}
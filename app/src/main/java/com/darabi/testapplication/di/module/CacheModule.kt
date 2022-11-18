package com.darabi.testapplication.di.module

import com.darabi.testapplication.cache.Cache
import com.darabi.testapplication.cache.CacheImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: CacheImpl): Cache
}
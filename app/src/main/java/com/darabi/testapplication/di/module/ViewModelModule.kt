package com.darabi.testapplication.di.module

import com.darabi.testapplication.repository.Repository
import com.darabi.testapplication.repository.RepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryImpl): Repository
}
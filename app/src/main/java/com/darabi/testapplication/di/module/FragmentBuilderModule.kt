package com.darabi.testapplication.di.module

import androidx.fragment.app.Fragment
import com.darabi.testapplication.di.annotation.FragmentKey
import com.darabi.testapplication.ui.home.HomeFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
abstract class FragmentBuilderModule {

    @Binds
    @IntoMap
    @FragmentKey(HomeFragment::class)
    abstract fun bindHomeFragment(fragment: HomeFragment): Fragment
}
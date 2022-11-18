package com.darabi.testapplication.ui.home

import androidx.lifecycle.liveData
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel() {

    fun getMonths() = liveData {
        emit(repository.getMonths())
    }
}
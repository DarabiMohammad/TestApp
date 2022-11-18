package com.darabi.testapplication.ui

import androidx.lifecycle.MutableLiveData
import com.darabi.testCustomView.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel() {

    val onFragmentBackPressed by lazy { MutableLiveData<String>() }
}
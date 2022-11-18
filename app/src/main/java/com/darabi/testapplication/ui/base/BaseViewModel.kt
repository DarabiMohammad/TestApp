package com.darabi.testCustomView.ui.base

import androidx.lifecycle.ViewModel
import com.darabi.testapplication.repository.Repository
import javax.inject.Inject

abstract class BaseViewModel : ViewModel() {

    @Inject
    protected lateinit var repository: Repository
}
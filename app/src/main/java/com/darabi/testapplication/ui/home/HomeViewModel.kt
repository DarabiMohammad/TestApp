package com.darabi.testapplication.ui.home

import com.darabi.testCustomView.ui.base.BaseViewModel
import com.darabi.testapplication.model.Month
import com.darabi.testapplication.ui.home.worker.WorkerHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import java.lang.reflect.Type
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val workerHandler: WorkerHandler,
    private val gson: Gson
) : BaseViewModel() {

    fun getMonths(delay: Long) = workerHandler.registerWork(delay)

    fun fromJson(json: String?): List<Month> = gson.fromJson(json, getType<List<Month>>())

    private inline fun <reified T> getType(): Type = object : TypeToken<T>() {}.type
}
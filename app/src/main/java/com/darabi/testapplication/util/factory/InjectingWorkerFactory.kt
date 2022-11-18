package com.darabi.testapplication.util.factory

import android.content.Context
import androidx.work.ListenableWorker
import androidx.work.WorkerFactory
import androidx.work.WorkerParameters
import com.darabi.testapplication.cache.database.DataBase
import com.darabi.testapplication.ui.home.worker.DelayedWorker
import com.google.gson.Gson
import javax.inject.Inject

class InjectingWorkerFactory @Inject constructor(
    private val dataBase: DataBase,
    private val gson: Gson,
) : WorkerFactory() {

    override fun createWorker(
        appContext: Context,
        workerClassName: String,
        workerParameters: WorkerParameters
    ): ListenableWorker? = DelayedWorker(appContext, workerParameters, dataBase, gson)
}
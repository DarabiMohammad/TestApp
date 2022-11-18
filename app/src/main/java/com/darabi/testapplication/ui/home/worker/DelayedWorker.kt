package com.darabi.testapplication.ui.home.worker

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.darabi.testapplication.cache.database.DataBase
import com.darabi.testapplication.model.Month
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import javax.inject.Inject

@HiltWorker
class DelayedWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val dataBase: DataBase,
    private val gson: Gson,
) : CoroutineWorker(context, workerParameters) {

    companion object {

        const val LIST_OF_MONTH = "list_of_month"
    }

    override suspend fun doWork(): Result {

        return Result.success(workDataOf(LIST_OF_MONTH to gson.toJson(dataBase.getMonths())))
    }
}
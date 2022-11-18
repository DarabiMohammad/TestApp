package com.darabi.testapplication.ui.home.worker

import androidx.lifecycle.LiveData
import androidx.work.OneTimeWorkRequest
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.google.gson.Gson
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerHandlerImpl @Inject constructor(
    private val workManager: WorkManager,
    private val gson: Gson,
) : WorkerHandler {

    private lateinit var request: OneTimeWorkRequest

    override fun registerWork(delay: Long): LiveData<WorkInfo> {
        request = makeRequest(delay)
        workManager.enqueue(request)
        return workManager.getWorkInfoByIdLiveData(request.id)
    }

    private fun makeRequest(delay: Long): OneTimeWorkRequest =
        OneTimeWorkRequestBuilder<DelayedWorker>().setInitialDelay(delay, TimeUnit.SECONDS).build()
}
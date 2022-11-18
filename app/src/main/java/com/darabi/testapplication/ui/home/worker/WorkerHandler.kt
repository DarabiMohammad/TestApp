package com.darabi.testapplication.ui.home.worker

import androidx.lifecycle.LiveData
import androidx.work.WorkInfo

interface WorkerHandler {

    fun registerWork(delay: Long): LiveData<WorkInfo>
}
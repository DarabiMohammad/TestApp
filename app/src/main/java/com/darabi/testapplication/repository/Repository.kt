package com.darabi.testapplication.repository

import com.darabi.testapplication.model.Month

interface Repository {

    suspend fun getMonths(): ResponseWrapper<List<Month>>
}
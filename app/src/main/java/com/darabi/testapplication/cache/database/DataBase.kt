package com.darabi.testapplication.cache.database

import com.darabi.testapplication.model.Month

interface DataBase {

    suspend fun getMonths(): List<Month>
}
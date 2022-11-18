package com.darabi.testapplication.repository

import com.darabi.testapplication.cache.Cache
import com.darabi.testapplication.model.Month
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RepositoryImpl @Inject constructor(private val cache: Cache) : Repository {

    override suspend fun getMonths(): ResponseWrapper<List<Month>> = cache.getMonths()
}
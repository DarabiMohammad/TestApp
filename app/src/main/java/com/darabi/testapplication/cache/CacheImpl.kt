package com.darabi.testapplication.cache

import com.darabi.testapplication.model.Month
import com.darabi.testapplication.repository.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CacheImpl @Inject constructor() : Cache {

    override suspend fun getMonths(): ResponseWrapper<List<Month>> = safeCall {
        generateFakeMonths()
    }

    /**
     * this function mocks data fetching. list of [Month] can be stored on shared prefs or
     * local database, which in this case, for simplicity is being generated in this function.
     */
    private fun generateFakeMonths(): List<Month> = listOf(

        Month("January", 1),
        Month("February", 2),
        Month("March", 3),
        Month("April", 4),
        Month("May", 5),
        Month("June", 6),
        Month("July", 7),
        Month("August", 8),
        Month("September", 9),
        Month("October", 10),
        Month("November", 11),
        Month("December", 12),
    )

    /**
     * this function wraps all calls to local storage e.g. shared prefs of database.
     * checks possible throwable errors and returns the result as [ResponseWrapper] instance.
     *
     * @param function is the error prone call to local storage which must be checked.
     *
     * @return [ResponseWrapper] which may include data or thrown error.
     */
    private suspend inline fun <T> safeCall(crossinline function: () -> T): ResponseWrapper<T> = try {

        withContext(Dispatchers.IO) {

            ResponseWrapper.Data(function.invoke())
        }
    } catch (exception: Exception) {

        withContext(Dispatchers.Main) {

            ResponseWrapper.Error(exception)
        }
    }
}
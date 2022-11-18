package com.darabi.testapplication.cache

import com.darabi.testapplication.cache.database.DataBase
import com.darabi.testapplication.model.Month
import com.darabi.testapplication.repository.Repository
import com.darabi.testapplication.repository.ResponseWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * The [Cache] interface represents all operations related to local persisting data.
 * the data flow from local storages (e.g, shared prefs, sqlite database, shared storage of the device or etc)
 * must be sent to [Repository] through this class. in other words, this class decides which type of
 * mentioned storages must be used for getting data from local.
 */
class CacheImpl @Inject constructor(
    private val dataBase: DataBase
) : Cache {

    override suspend fun getMonths(): ResponseWrapper<List<Month>> = safeCall {
        dataBase.getMonths()
    }

    /**
     * this function wraps all calls to local storage e.g. shared prefs of database.
     * checks possible throwable errors and returns the result as [ResponseWrapper] instance.
     *
     * @param function is the error prone call to local storage which must be checked.
     *
     * @return [ResponseWrapper] which may include data or thrown error.
     */
    private suspend inline fun <T> safeCall(crossinline function: suspend () -> T): ResponseWrapper<T> = try {

        withContext(Dispatchers.IO) {

            ResponseWrapper.Data(function.invoke())
        }
    } catch (exception: Exception) {

        withContext(Dispatchers.Main) {

            ResponseWrapper.Error(exception)
        }
    }
}
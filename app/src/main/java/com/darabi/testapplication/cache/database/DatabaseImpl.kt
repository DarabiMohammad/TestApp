package com.darabi.testapplication.cache.database

import com.darabi.testapplication.model.Month
import javax.inject.Inject

class DatabaseImpl @Inject constructor() : DataBase {

    /**
     * this function mocks data fetching. list of [Month] can be stored on shared prefs or
     * local database, which in this case, for simplicity is being generated in this function.
     */
    override suspend fun getMonths(): List<Month> = listOf (

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
}
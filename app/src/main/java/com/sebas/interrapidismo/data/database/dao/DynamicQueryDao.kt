package com.sebas.interrapidismo.data.database.dao

import android.database.Cursor
import androidx.room.Dao
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
@Dao
interface DynamicQueryDao {
    @RawQuery
    fun executeQuery(query: SupportSQLiteQuery): Cursor
}
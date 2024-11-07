package com.sebas.interrapidismo.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity

@Dao
interface TableSchemaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSchemaTables(schemaTables: List<TableSchemaEntity>)

    @Query("SELECT nombre_tabla FROM table_schema")
    fun getAllSchemaTables(): List<String>

    @Query("DELETE FROM table_schema")
    fun clearSchemaTables()
}
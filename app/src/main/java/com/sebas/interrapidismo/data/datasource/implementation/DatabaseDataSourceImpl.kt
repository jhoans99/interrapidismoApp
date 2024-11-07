package com.sebas.interrapidismo.data.datasource.implementation

import android.database.Cursor
import androidx.room.withTransaction
import androidx.sqlite.db.SimpleSQLiteQuery
import com.sebas.interrapidismo.commons.NetworkExecutor
import com.sebas.interrapidismo.data.database.AppDatabase
import com.sebas.interrapidismo.data.database.dao.DynamicQueryDao
import com.sebas.interrapidismo.data.database.dao.TableSchemaDao
import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.data.datasource.DatabaseDataSource
import com.sebas.interrapidismo.network.api.DataBaseApi
import com.sebas.interrapidismo.network.model.response.TableSchema
import javax.inject.Inject

class DatabaseDataSourceImpl @Inject constructor(
    private val dataBaseApi: DataBaseApi,
    private val networkExecutor: NetworkExecutor,
    private val appDatabase: AppDatabase,
    private val tableSchemaDao: TableSchemaDao,
    private val dynamicQueryDao: DynamicQueryDao
): DatabaseDataSource {
    override suspend fun fetchSchemeDataBase(): ArrayList<TableSchema> {
        return networkExecutor.fetch { dataBaseApi.fetchSchemaDataBase() }
    }

    override suspend fun insertSchema(response: ArrayList<TableSchema>) {
        val schemaTables = response.map { table ->
            TableSchemaEntity(
                tableName = table.nameTable,
                pk = table.pk,
                creationQuery = table.queryCreation,
                batchSize = table.batchSize,
                filter = table.filter,
                error = table.error,
                fieldsNumber = table.numberFields,
                appMethod = table.appMethod,
                updateDate = table.dateUpdate
            )
        }
        appDatabase.withTransaction {
            tableSchemaDao.clearSchemaTables()
            tableSchemaDao.insertSchemaTables(schemaTables)
            schemaTables.forEach { tabla ->
                appDatabase.openHelper.writableDatabase.execSQL(tabla.creationQuery)
            }
        }
    }

    override suspend fun getAllSchema(): List<String> {
        return tableSchemaDao.getAllSchemaTables()
    }

    override suspend fun getDataByTable(table: String): List<Map<String, Any?>> {
        val query = SimpleSQLiteQuery("SELECT * FROM $table")
        val cursor = dynamicQueryDao.executeQuery(query)
        return cursor.use {
            val dataList = mutableListOf<Map<String, Any?>>()
            while (it.moveToNext()) {
                val row = mutableMapOf<String, Any?>()
                for (i in 0 until it.columnCount) {
                    row[it.getColumnName(i)] = when (it.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> it.getInt(i)
                        Cursor.FIELD_TYPE_FLOAT -> it.getFloat(i)
                        Cursor.FIELD_TYPE_STRING -> it.getString(i)
                        Cursor.FIELD_TYPE_BLOB -> it.getBlob(i)
                        else -> null
                    }
                }
                dataList.add(row)
            }
            dataList
        }
    }
}
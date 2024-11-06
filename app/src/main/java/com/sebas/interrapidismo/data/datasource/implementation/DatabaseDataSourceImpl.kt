package com.sebas.interrapidismo.data.datasource.implementation

import androidx.room.withTransaction
import com.sebas.interrapidismo.commons.NetworkExecutor
import com.sebas.interrapidismo.data.database.AppDatabase
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
    private val tableSchemaDao: TableSchemaDao
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
        }
    }

    override suspend fun getAllSchema(): List<TableSchemaEntity> {
        return tableSchemaDao.getAllSchemaTables()
    }
}
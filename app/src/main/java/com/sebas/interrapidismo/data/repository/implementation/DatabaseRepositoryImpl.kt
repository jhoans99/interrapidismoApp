package com.sebas.interrapidismo.data.repository.implementation

import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.data.datasource.DatabaseDataSource
import com.sebas.interrapidismo.data.repository.DatabaseRepository
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(
    private val databaseDataSource: DatabaseDataSource
): DatabaseRepository {
    override suspend fun fetchSchemaDatabase() {
        val response = databaseDataSource.fetchSchemeDataBase()
        databaseDataSource.insertSchema(response)
    }

    override suspend fun getAllSchema(): List<TableSchemaEntity> = databaseDataSource.getAllSchema()
}

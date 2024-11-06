package com.sebas.interrapidismo.data.datasource

import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.network.model.response.TableSchema

interface DatabaseDataSource {
    suspend fun fetchSchemeDataBase(): ArrayList<TableSchema>

    suspend fun insertSchema(response: ArrayList<TableSchema>)

    suspend fun getAllSchema(): List<TableSchemaEntity>
}
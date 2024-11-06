package com.sebas.interrapidismo.data.repository

import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.network.model.response.TableSchema

interface DatabaseRepository {
    suspend fun fetchSchemaDatabase()

    suspend fun getAllSchema(): List<TableSchemaEntity>
}
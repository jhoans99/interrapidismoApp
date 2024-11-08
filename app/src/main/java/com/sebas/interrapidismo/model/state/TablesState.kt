package com.sebas.interrapidismo.model.state

import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity

data class TablesState(
    val isLoading: Boolean = false,
    val listSchema: List<String> = emptyList(),
    val listDataTable: List<Map<String, Any?>> = emptyList()
)

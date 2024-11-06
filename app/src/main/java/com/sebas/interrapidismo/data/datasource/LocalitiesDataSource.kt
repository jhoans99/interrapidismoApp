package com.sebas.interrapidismo.data.datasource

import com.sebas.interrapidismo.network.model.response.LocalitiesResponse

interface LocalitiesDataSource {
    suspend fun fetchLocalities(): ArrayList<LocalitiesResponse>
}
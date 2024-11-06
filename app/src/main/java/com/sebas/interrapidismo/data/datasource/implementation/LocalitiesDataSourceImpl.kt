package com.sebas.interrapidismo.data.datasource.implementation

import com.sebas.interrapidismo.commons.NetworkExecutor
import com.sebas.interrapidismo.data.datasource.LocalitiesDataSource
import com.sebas.interrapidismo.network.api.LocalitiesApi
import com.sebas.interrapidismo.network.model.response.LocalitiesResponse
import javax.inject.Inject

class LocalitiesDataSourceImpl @Inject constructor(
    private val localitiesApi: LocalitiesApi,
    private val networkExecutor: NetworkExecutor
): LocalitiesDataSource {
    override suspend fun fetchLocalities(): ArrayList<LocalitiesResponse> {
        return networkExecutor.fetch(localitiesApi::fetchLocalities)
    }
}
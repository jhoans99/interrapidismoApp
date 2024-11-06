package com.sebas.interrapidismo.data.datasource.implementation

import com.sebas.interrapidismo.commons.NetworkExecutor
import com.sebas.interrapidismo.data.datasource.SecurityDataSource
import com.sebas.interrapidismo.network.api.SecurityApi
import retrofit2.Response
import javax.inject.Inject

class SecurityDataSourceImpl @Inject constructor(
    private val securityApi: SecurityApi,
    private val networkExecutor: NetworkExecutor
): SecurityDataSource {

    override suspend fun fetchAppVersion(): String {
        return networkExecutor.fetch(securityApi::fetchAppVersion)
    }
}
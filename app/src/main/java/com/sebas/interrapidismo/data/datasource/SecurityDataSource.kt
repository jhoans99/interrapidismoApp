package com.sebas.interrapidismo.data.datasource

import retrofit2.Response

interface SecurityDataSource {
    suspend fun fetchAppVersion(): String
}
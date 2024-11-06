package com.sebas.interrapidismo.data.repository

import com.sebas.interrapidismo.commons.Result
import kotlinx.coroutines.flow.Flow

interface SecurityRepository {
    suspend fun fetchAppVersion(): Flow<Result<String>>
}
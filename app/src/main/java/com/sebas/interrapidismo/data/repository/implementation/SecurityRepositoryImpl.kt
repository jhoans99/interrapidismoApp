package com.sebas.interrapidismo.data.repository.implementation

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.datasource.SecurityDataSource
import com.sebas.interrapidismo.data.repository.SecurityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SecurityRepositoryImpl @Inject constructor(
    private val securityDataSource: SecurityDataSource
): SecurityRepository {
    override suspend fun fetchAppVersion(): Flow<Result<String>> = flow {
        emit(Result.Loading)
        val response = securityDataSource.fetchAppVersion()
        emit(Result.Success(response ?: ""))
    }.catch {
        emit(Result.Error(it.message ?: "Generic error"))
    }
}
package com.sebas.interrapidismo.data.repository.implementation

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.commons.toExceptions
import com.sebas.interrapidismo.data.datasource.LocalitiesDataSource
import com.sebas.interrapidismo.data.repository.LocalitiesRepository
import com.sebas.interrapidismo.network.model.response.LocalitiesResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LocalitiesRepositoryImpl @Inject constructor(
    private val localitiesDataSource: LocalitiesDataSource
): LocalitiesRepository {

    override suspend fun fetchLocalities(): Flow<Result<ArrayList<LocalitiesResponse>>> = flow {
        emit(Result.Loading)
        val response = localitiesDataSource.fetchLocalities()
        emit(Result.Success(response))
    }.catch {
        emit(Result.Error(it.toExceptions().message ?: "Generic Error"))
    }
}
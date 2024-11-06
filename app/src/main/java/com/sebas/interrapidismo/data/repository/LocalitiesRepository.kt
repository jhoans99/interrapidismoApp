package com.sebas.interrapidismo.data.repository

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.network.model.response.LocalitiesResponse
import kotlinx.coroutines.flow.Flow

interface LocalitiesRepository {
    suspend fun fetchLocalities(): Flow<Result<ArrayList<LocalitiesResponse>>>
}
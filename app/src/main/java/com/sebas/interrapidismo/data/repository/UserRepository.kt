package com.sebas.interrapidismo.data.repository

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.database.entity.UserEntity
import com.sebas.interrapidismo.network.model.request.LoginRequest
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun login(loginRequest: LoginRequest): Flow<Result<Boolean>>

    suspend fun insertUser(userEntity: UserEntity): Flow<Boolean>

    suspend fun fetchInfoUserLocal(): Flow<Result<UserEntity>>
}
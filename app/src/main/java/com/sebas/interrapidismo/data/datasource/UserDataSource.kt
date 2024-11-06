package com.sebas.interrapidismo.data.datasource

import com.sebas.interrapidismo.data.database.entity.UserEntity
import com.sebas.interrapidismo.network.model.request.LoginRequest
import com.sebas.interrapidismo.network.model.response.LoginResponse

interface UserDataSource {
    suspend fun login(loginRequest: LoginRequest): LoginResponse
    suspend fun insertUser(userEntity: UserEntity)
    suspend fun fetchInfoUserLocal(): UserEntity
}
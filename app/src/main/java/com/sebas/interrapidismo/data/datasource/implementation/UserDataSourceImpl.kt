package com.sebas.interrapidismo.data.datasource.implementation

import com.sebas.interrapidismo.commons.NetworkExecutor
import com.sebas.interrapidismo.data.database.entity.UserEntity
import com.sebas.interrapidismo.data.database.dao.UserDao
import com.sebas.interrapidismo.data.datasource.UserDataSource
import com.sebas.interrapidismo.network.api.UserApi
import com.sebas.interrapidismo.network.model.request.LoginRequest
import com.sebas.interrapidismo.network.model.response.LoginResponse
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val userApi: UserApi,
    private val networkExecutor: NetworkExecutor,
    private val userDao: UserDao
): UserDataSource {

    override suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return networkExecutor.fetch { userApi.login(loginRequest) }
    }

    override suspend fun insertUser(userEntity: UserEntity) = userDao.insertUser(userEntity)

    override suspend fun fetchInfoUserLocal(): UserEntity = userDao.fetchUserLocal()
}
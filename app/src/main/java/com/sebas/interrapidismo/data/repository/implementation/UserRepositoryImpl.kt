package com.sebas.interrapidismo.data.repository.implementation

import android.util.Log
import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.commons.toExceptions
import com.sebas.interrapidismo.data.database.entity.UserEntity
import com.sebas.interrapidismo.data.datasource.UserDataSource
import com.sebas.interrapidismo.data.repository.UserRepository
import com.sebas.interrapidismo.network.model.request.LoginRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
): UserRepository {

    override suspend fun login(loginRequest: LoginRequest): Flow<Result<Boolean>> = flow {
        emit(Result.Loading)
        val loginResponse = userDataSource.login(loginRequest)
        insertUser(
            UserEntity(
                user = loginResponse.user,
                names = loginResponse.name,
                identification = loginResponse.identification
            )
        ).collect {
            emit(Result.Success(it))
        }
    }.catch {
        emit(Result.Error(it.toExceptions().message ?: "Generic Error"))
    }

    override suspend fun insertUser(userEntity: UserEntity): Flow<Boolean> = flow {
        userDataSource.insertUser(userEntity)
        emit(true)
    }.catch {
        Log.d("TAG--", "insertUser: ${it}")
        emit(false)
    }.flowOn(Dispatchers.IO)

    override suspend fun fetchInfoUserLocal(): Flow<Result<UserEntity>> = flow {
        emit(Result.Loading)
        val fetchInfoUser = userDataSource.fetchInfoUserLocal()
        emit(Result.Success(fetchInfoUser))
    }.catch {
        emit(Result.Error(it.toExceptions().message ?: ""))
    }
}
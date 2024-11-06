package com.sebas.interrapidismo.domain.usecases

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.repository.UserRepository
import com.sebas.interrapidismo.network.model.request.LoginRequest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository,
    private val encodeCredentialsUseCase: EncodeCredentialsUseCase
){
    suspend fun login(userName: String, password: String): Flow<Result<Boolean>> {
        val request = LoginRequest(
            username = encodeCredentialsUseCase(userName),
            password = encodeCredentialsUseCase(password)
        )
       return userRepository.login(request)
    }
}
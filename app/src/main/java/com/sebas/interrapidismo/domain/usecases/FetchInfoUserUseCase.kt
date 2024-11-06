package com.sebas.interrapidismo.domain.usecases

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.database.entity.UserEntity
import com.sebas.interrapidismo.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchInfoUserUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(): Flow<Result<UserEntity>> = userRepository.fetchInfoUserLocal()
}
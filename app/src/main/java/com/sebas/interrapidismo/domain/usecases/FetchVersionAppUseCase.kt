package com.sebas.interrapidismo.domain.usecases

import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.repository.SecurityRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FetchVersionAppUseCase @Inject constructor(
    private val securityRepository: SecurityRepository
){
    suspend operator fun invoke(): Flow<Result<String>> = securityRepository.fetchAppVersion()
}
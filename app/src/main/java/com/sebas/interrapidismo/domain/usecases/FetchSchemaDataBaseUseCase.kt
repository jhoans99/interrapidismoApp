package com.sebas.interrapidismo.domain.usecases

import com.sebas.interrapidismo.data.repository.DatabaseRepository
import javax.inject.Inject

class FetchSchemaDataBaseUseCase @Inject constructor(
    private val databaseRepository: DatabaseRepository
) {
    suspend fun fetchSchemaDataBase() {
        if(databaseRepository.getAllSchema().isEmpty()) {
            databaseRepository.fetchSchemaDatabase()
        }
    }
}
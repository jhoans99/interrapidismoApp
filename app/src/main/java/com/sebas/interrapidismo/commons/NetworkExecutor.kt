package com.sebas.interrapidismo.commons

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
@Singleton
class NetworkExecutor @Inject constructor() {
    suspend fun <T> fetch(api: suspend () -> Response<T>): T = withContext(Dispatchers.IO) {
        val response = api()

        if(response.isSuccessful) {
            response.body() as T
        } else {
            throw NetworkFailedResponseException()
        }
    }

}

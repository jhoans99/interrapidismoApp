package com.sebas.interrapidismo.network.api

import retrofit2.Response
import retrofit2.http.GET

interface SecurityApi {

    @GET("apicontrollerpruebas/api/ParametrosFramework/ConsultarParametrosFramework/VPStoreAppControl")
    suspend fun fetchAppVersion(): Response<String>
}
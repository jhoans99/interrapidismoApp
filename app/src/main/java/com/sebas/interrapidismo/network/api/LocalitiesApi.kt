package com.sebas.interrapidismo.network.api

import com.sebas.interrapidismo.network.model.response.LocalitiesResponse
import retrofit2.Response
import retrofit2.http.GET

interface LocalitiesApi {

    @GET("apicontrollerpruebas/api/ParametrosFramework/ObtenerLocalidadesRecogidas")
    suspend fun fetchLocalities(): Response<ArrayList<LocalitiesResponse>>
}
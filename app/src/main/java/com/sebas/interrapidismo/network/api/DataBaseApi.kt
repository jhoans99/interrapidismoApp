package com.sebas.interrapidismo.network.api

import com.sebas.interrapidismo.network.model.request.LoginRequest
import com.sebas.interrapidismo.network.model.response.LoginResponse
import com.sebas.interrapidismo.network.model.response.TableSchema
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface DataBaseApi {

    @Headers(
        "Usuario: pam.meredy21",
        "Identificacion: 987204545",
        "Accept: text/json",
        "IdUsuario: pam.meredy21",
        "IdCentroServicio: 1295",
        "NombreCentroServicio: PTO/BOGOTA/CUND/COL/OF PRINCIPAL - CRA 30# 7-45",
        "IdAplicativoOrigen: 9",
        "Content-Type: application/json"
    )
    @GET("apicontrollerpruebas/api/SincronizadorDatos/ObtenerEsquema/true")
    suspend fun fetchSchemaDataBase(): Response<ArrayList<TableSchema>>
}
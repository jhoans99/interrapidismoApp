package com.sebas.interrapidismo.network.api

import com.sebas.interrapidismo.network.model.request.LoginRequest
import com.sebas.interrapidismo.network.model.response.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UserApi {

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
    @POST("FtEntregaElectronica/MultiCanales/ApiSeguridadPruebas/api/Seguridad/AuthenticaUsuarioApp")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

}
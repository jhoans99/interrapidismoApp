package com.sebas.interrapidismo.network.model.request

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("Mac")
    val mac: String = "",
    @SerializedName("NomAplicacion")
    val nameApplication: String = "Controller APP",
    @SerializedName("Password")
    val password: String,
    @SerializedName("Path")
    val path: String = "",
    @SerializedName("Usuario")
    val username: String
)
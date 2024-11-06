package com.sebas.interrapidismo.network.model.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("Usuario")
    val user: String,
    @SerializedName("Identificacion")
    val identification: String,
    @SerializedName("Nombre")
    val name: String
)
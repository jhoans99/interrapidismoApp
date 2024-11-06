package com.sebas.interrapidismo.network.model.response

import com.google.gson.annotations.SerializedName

data class LocalitiesResponse(
    @SerializedName("AbreviacionCiudad")
    val abbreviationCity: String,
    @SerializedName("NombreCompleto")
    val nameCity: String
)

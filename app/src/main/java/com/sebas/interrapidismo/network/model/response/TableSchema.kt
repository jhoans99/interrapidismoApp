package com.sebas.interrapidismo.network.model.response

import com.google.gson.annotations.SerializedName

data class TableSchema(
    @SerializedName("NombreTabla")
    val nameTable: String,
    @SerializedName("Pk")
    val pk: String,
    @SerializedName("QueryCreacion")
    val queryCreation: String,
    @SerializedName("BatchSize")
    val batchSize: Int,
    @SerializedName("Filtro")
    val filter: String,
    @SerializedName("error")
    val error: String?,
    @SerializedName("NumeroCampos")
    val numberFields: Int,
    @SerializedName("MetodoApp")
    val appMethod: String?,
    @SerializedName("FechaActualizacionSincro")
    val dateUpdate: String
)

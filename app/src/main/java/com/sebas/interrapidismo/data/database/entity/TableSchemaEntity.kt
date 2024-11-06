package com.sebas.interrapidismo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_schema")
data class TableSchemaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "nombre_tabla") val tableName: String,
    @ColumnInfo(name = "pk") val pk: String,
    @ColumnInfo(name = "query_creacion") val creationQuery: String,
    @ColumnInfo(name = "batch_size") val batchSize: Int,
    @ColumnInfo(name = "filtro") val filter: String?,
    @ColumnInfo(name = "error") val error: String?,
    @ColumnInfo(name = "numero_campos") val fieldsNumber: Int,
    @ColumnInfo(name = "metodo_app") val appMethod: String?,
    @ColumnInfo(name = "fecha_actualizacion_sincro") val updateDate: String
)
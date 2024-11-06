package com.sebas.interrapidismo.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo("user") val user: String,
    @ColumnInfo("identification") val identification: String,
    @ColumnInfo("names") val names: String
)

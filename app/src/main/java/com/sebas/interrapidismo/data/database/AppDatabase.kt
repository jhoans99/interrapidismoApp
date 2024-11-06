package com.sebas.interrapidismo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sebas.interrapidismo.data.database.dao.TableSchemaDao
import com.sebas.interrapidismo.data.database.dao.UserDao
import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.data.database.entity.UserEntity

@Database(
    entities = [
        UserEntity::class,
        TableSchemaEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun tableSchemaDao(): TableSchemaDao
}
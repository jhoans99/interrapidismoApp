package com.sebas.interrapidismo.di

import android.content.Context
import androidx.room.Room
import com.sebas.interrapidismo.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val APP_DATABASE_NAME = "interrapidisimo_database"

    @Singleton
    @Provides
    fun provideRoomDataBase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        APP_DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideUserDao(
        database: AppDatabase
    ) = database.userDao()

    @Singleton
    @Provides
    fun provideTableSchemaDao(
        database: AppDatabase
    ) = database.tableSchemaDao()

    @Singleton
    @Provides
    fun provideDynamicQueryDao(
        database: AppDatabase
    ) = database.DynamicQueryDao()
}
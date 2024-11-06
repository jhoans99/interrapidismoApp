package com.sebas.interrapidismo.di

import com.google.gson.GsonBuilder
import com.sebas.interrapidismo.network.api.DataBaseApi
import com.sebas.interrapidismo.network.api.LocalitiesApi
import com.sebas.interrapidismo.network.api.SecurityApi
import com.sebas.interrapidismo.network.api.UserApi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://apitesting.interrapidisimo.co/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    @Singleton
    fun provideSecurityApi(
        retrofit: Retrofit
    ): SecurityApi =
        retrofit.create(SecurityApi::class.java)

    @Provides
    @Singleton
    fun provideUserApi(
        retrofit: Retrofit
    ): UserApi =
        retrofit.create(UserApi::class.java)

    @Provides
    @Singleton
    fun provideDatabaseApi(
        retrofit: Retrofit
    ): DataBaseApi = retrofit.create(DataBaseApi::class.java)

    @Provides
    @Singleton
    fun providesLocalitiesApi(
        retrofit: Retrofit
    ): LocalitiesApi = retrofit.create(LocalitiesApi::class.java)
}
package com.sebas.interrapidismo.di

import com.sebas.interrapidismo.data.repository.DatabaseRepository
import com.sebas.interrapidismo.data.repository.LocalitiesRepository
import com.sebas.interrapidismo.data.repository.implementation.DatabaseRepositoryImpl
import com.sebas.interrapidismo.data.repository.SecurityRepository
import com.sebas.interrapidismo.data.repository.UserRepository
import com.sebas.interrapidismo.data.repository.implementation.LocalitiesRepositoryImpl
import com.sebas.interrapidismo.data.repository.implementation.SecurityRepositoryImpl
import com.sebas.interrapidismo.data.repository.implementation.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindSecurityModule(
        securityRepositoryImpl: SecurityRepositoryImpl
    ): SecurityRepository

    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindDataBaseRepository(
        databaseRepositoryImpl: DatabaseRepositoryImpl
    ): DatabaseRepository

    @Binds
    @Singleton
    abstract fun bindLocalitiesRepository(
        localitiesRepositoryImpl: LocalitiesRepositoryImpl
    ): LocalitiesRepository
}
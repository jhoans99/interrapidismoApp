package com.sebas.interrapidismo.di

import com.sebas.interrapidismo.data.datasource.DatabaseDataSource
import com.sebas.interrapidismo.data.datasource.LocalitiesDataSource
import com.sebas.interrapidismo.data.datasource.SecurityDataSource
import com.sebas.interrapidismo.data.datasource.UserDataSource
import com.sebas.interrapidismo.data.datasource.implementation.DatabaseDataSourceImpl
import com.sebas.interrapidismo.data.datasource.implementation.LocalitiesDataSourceImpl
import com.sebas.interrapidismo.data.datasource.implementation.SecurityDataSourceImpl
import com.sebas.interrapidismo.data.datasource.implementation.UserDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindDatasourceModule(
        securityDataSourceImpl: SecurityDataSourceImpl
    ): SecurityDataSource

    @Binds
    @Singleton
    abstract fun bindUserDataSource(
        userDataSourceImpl: UserDataSourceImpl
    ): UserDataSource

    @Binds
    @Singleton
    abstract fun bindDatabaseDataSource(
        databaseDataSourceImpl: DatabaseDataSourceImpl
    ): DatabaseDataSource

    @Binds
    @Singleton
    abstract fun bindLocalitiesDataSource(
        localitiesDataSourceImpl: LocalitiesDataSourceImpl
    ): LocalitiesDataSource
}
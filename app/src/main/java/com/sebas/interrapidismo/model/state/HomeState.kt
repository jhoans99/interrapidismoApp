package com.sebas.interrapidismo.model.state

import com.sebas.interrapidismo.data.database.entity.UserEntity

data class HomeState(
    val userInformation: UserInformation = UserInformation(),
    val isLoading: Boolean = false
)

data class UserInformation(
    val user: String = "",
    val identification: String = "",
    val names: String = ""
)

fun UserEntity.toUserInformation() = UserInformation (
    user, identification, names
)
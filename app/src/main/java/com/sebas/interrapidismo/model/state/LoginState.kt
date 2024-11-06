package com.sebas.interrapidismo.model.state

import com.sebas.interrapidismo.ui.features.login.VersionAppState

data class LoginState(
    val isLoading: Boolean = false,
    val versionAppState: VersionAppState? = null,
    val userName: String = "pam.meredy21",
    val password: String = "Inter2021",
    val errorLogin: String? = null
)

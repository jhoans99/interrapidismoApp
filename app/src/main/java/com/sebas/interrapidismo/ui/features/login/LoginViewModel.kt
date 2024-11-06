package com.sebas.interrapidismo.ui.features.login

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.interrapidismo.BuildConfig
import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.domain.usecases.FetchVersionAppUseCase
import com.sebas.interrapidismo.domain.usecases.LoginUseCase
import com.sebas.interrapidismo.model.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val fetchVersionAppUseCase: FetchVersionAppUseCase,
    private val loginUseCase: LoginUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState

    init {
        viewModelScope.launch {
            fetchVersionAppUseCase().collect {
                when(it) {
                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                    Result.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            versionAppState = validateVersionApp(it.data)
                        )
                    }
                }
            }
        }
    }

    private fun validateVersionApp(versionRemoteApp: String): VersionAppState? {
        return when {
            versionRemoteApp.toInt() > BuildConfig.VERSION_CODE -> {
                VersionAppState.LOWER_VERSION
            }
            versionRemoteApp.toInt() < BuildConfig.VERSION_CODE -> {
                VersionAppState.UPPER_VERSION
            }

            else -> {
                null
            }
        }
    }

    fun login(
        onNavigateHome: () -> Unit
    ) {
        viewModelScope.launch {
                loginUseCase.login(_uiState.value.userName,_uiState.value.password).collect {
                    _uiState.value = when(it) {
                        is Result.Error -> {
                            _uiState.value.copy(isLoading = false, errorLogin = it.message)
                        }
                        Result.Loading -> {
                            _uiState.value.copy(isLoading = true)
                        }
                        is Result.Success -> {
                            onNavigateHome()
                            _uiState.value.copy(isLoading = false)
                        }
                    }
            }
        }
    }

    fun onUpdateUserName(value: String) {
        _uiState.value = _uiState.value.copy(userName = value)
    }

    fun onUpdatePassword(value: String) {
        _uiState.value = _uiState.value.copy(password = value)
    }

    fun onDismissModal() {
        _uiState.value = _uiState.value.copy(versionAppState = null, errorLogin = null)
    }
}
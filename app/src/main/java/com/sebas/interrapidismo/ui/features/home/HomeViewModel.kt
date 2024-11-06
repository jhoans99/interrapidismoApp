package com.sebas.interrapidismo.ui.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.repository.DatabaseRepository
import com.sebas.interrapidismo.domain.usecases.FetchInfoUserUseCase
import com.sebas.interrapidismo.model.state.HomeState
import com.sebas.interrapidismo.model.state.toUserInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository,
    private val fetchInfoUserUseCase: FetchInfoUserUseCase
): ViewModel() {

    private val _uiState = MutableStateFlow(HomeState())
    val uiState: StateFlow<HomeState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
            databaseRepository.fetchSchemaDatabase()
            fetchInfoUserUseCase().collect {
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
                            userInformation = it.data.toUserInformation()
                        )
                    }
                }
            }
        }
    }

}
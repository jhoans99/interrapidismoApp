package com.sebas.interrapidismo.ui.features.localities

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.interrapidismo.commons.Result
import com.sebas.interrapidismo.data.repository.LocalitiesRepository
import com.sebas.interrapidismo.model.state.LocalitiesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocalitiesViewModel @Inject constructor(
    private val localitiesRepository: LocalitiesRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LocalitiesState())
    val uiState: StateFlow<LocalitiesState> = _uiState

    init {
        viewModelScope.launch {
            localitiesRepository.fetchLocalities().collect {
                when(it) {
                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(isLoading = false)
                    }
                    Result.Loading -> {
                        _uiState.value = _uiState.value.copy(isLoading = true)
                    }
                    is Result.Success -> {
                        _uiState.value = _uiState.value.copy(listLocalitiesState = it.data, isLoading = false)
                    }
                }
            }
        }
    }
}
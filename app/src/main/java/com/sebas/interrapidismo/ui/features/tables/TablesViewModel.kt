package com.sebas.interrapidismo.ui.features.tables

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebas.interrapidismo.data.repository.DatabaseRepository
import com.sebas.interrapidismo.model.state.TablesState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TablesViewModel @Inject constructor(
    private val databaseRepository: DatabaseRepository
): ViewModel() {
    private val _uiState = MutableStateFlow(TablesState())
    val uiState: StateFlow<TablesState> = _uiState

    init {
        viewModelScope.launch(Dispatchers.IO) {
          _uiState.value = _uiState.value.copy(listSchema = databaseRepository.getAllSchema())
        }
    }

    fun fetchDataByTable(tableName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = _uiState.value.copy(listDataTable = databaseRepository.getDataByTable(tableName))
        }
    }
}
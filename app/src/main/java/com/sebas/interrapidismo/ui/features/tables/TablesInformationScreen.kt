package com.sebas.interrapidismo.ui.features.tables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sebas.interrapidismo.model.state.TablesState

@Composable
fun TablesInformationScreen(
    navController: NavHostController,
    nameTable: String,
    tablesViewModel: TablesViewModel = hiltViewModel()
) {
    val uiState by tablesViewModel.uiState.collectAsState()
    tablesViewModel.fetchDataByTable(nameTable)
    TablesInformation(uiState,nameTable) {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TablesInformation(uiState: TablesState,nameTable: String,onNavigateBackStack: ()-> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = nameTable)
                },
                navigationIcon = {
                    IconButton(onClick = { onNavigateBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            TablesInformationContent(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 24.dp),
                uiState
            )
        }
    }
}


@Composable
fun TablesInformationContent(modifier: Modifier, uiState: TablesState) {
    when {
        uiState.listDataTable.isNotEmpty() -> {
            LazyColumn(modifier) {
                items(uiState.listDataTable) { row ->
                    Column(modifier = Modifier.padding(8.dp)) {
                        row.forEach { (columnName, value) ->
                            Text(text = "$columnName: $value", style = MaterialTheme.typography.bodyMedium)
                        }
                        Divider(modifier = Modifier.padding(vertical = 4.dp))
                    }
                }
            }
        }
        else -> {
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
                    Text(text = "La tabla no contiene datos", style = MaterialTheme.typography.titleMedium)
                }
            }
        }
    }

}
package com.sebas.interrapidismo.ui.features.tables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sebas.interrapidismo.R
import com.sebas.interrapidismo.data.database.entity.TableSchemaEntity
import com.sebas.interrapidismo.model.state.TablesState
import com.sebas.interrapidismo.ui.components.LoaderComponent
import com.sebas.interrapidismo.ui.navigation.TableInformation

@Composable
fun TablesScreen(
    navController: NavHostController,
    tablesViewModel: TablesViewModel = hiltViewModel()
) {
    val uiState by tablesViewModel.uiState.collectAsState()

    when {
        uiState.isLoading -> LoaderComponent()
    }

    Tables(
        uiState,
        navigateBackStack = {
            navController.popBackStack()
        }
    ){
        navController.navigate(TableInformation(it))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Tables(
    uiState: TablesState,
    navigateBackStack: () -> Unit,
    navigateToTableData: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.text_tables))
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) {
        TablesContent(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            uiState.listSchema
        ) {
            navigateToTableData(it)
        }
    }
}

@Composable
fun TablesContent(
    modifier: Modifier,
    listSchema: List<String>,
    navigateToTableData: (String) -> Unit
) {
    Column(modifier = modifier
        .fillMaxSize()
        .padding(horizontal = 24.dp)
    ) {
        Text(text = "Selecciona una tabla para ver los detalles", style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn {
            items(listSchema) { item ->
                ListItem(item) {
                    navigateToTableData(item)
                }
            }
        }
    }
}

@Composable
fun ListItem(
    item: String,
    onItemSelected: (String) -> Unit
) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onItemSelected(item)
        }
        .padding(15.dp)
    ) {
        Text(stringResource(id = R.string.text_table_name,item), style = MaterialTheme.typography.bodyMedium)
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
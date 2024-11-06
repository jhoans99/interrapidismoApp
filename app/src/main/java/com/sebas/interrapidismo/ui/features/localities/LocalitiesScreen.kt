package com.sebas.interrapidismo.ui.features.localities

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
import com.sebas.interrapidismo.model.state.LocalitiesState
import com.sebas.interrapidismo.network.model.response.LocalitiesResponse
import com.sebas.interrapidismo.ui.components.LoaderComponent

@Composable
fun LocalitiesScreen(
    navController: NavHostController,
    localitiesViewModel: LocalitiesViewModel = hiltViewModel()
) {
    val uiState by localitiesViewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> LoaderComponent()
    }

    Localities(uiState) {
        navController.popBackStack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Localities(
    uiState: LocalitiesState,
    navigateBackStack: () -> Unit
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.text_localities))
                },
                navigationIcon = {
                    IconButton(onClick = { navigateBackStack() }) {
                        Icon(imageVector = Icons.Filled.ArrowBackIosNew, contentDescription = null)
                    }
                }
            )
        }
    ) {
        LocalitiesContent(modifier = Modifier.padding(it),uiState)
    }
}

@Composable
fun LocalitiesContent(modifier: Modifier, uiState: LocalitiesState) {
    LazyColumn(modifier = modifier.padding(horizontal = 24.dp)) {
        items(uiState.listLocalitiesState) { locality ->
            ListItemLocalities(locality)
        }
    }
}


@Composable
fun ListItemLocalities(locality: LocalitiesResponse) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Text(stringResource(id = R.string.text_abbreviation_city,locality.abbreviationCity))
        Text(stringResource(id = R.string.text_completed_name,locality.nameCity))
        Spacer(modifier = Modifier.height(8.dp))
        Divider(
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
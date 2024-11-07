package com.sebas.interrapidismo.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CardTravel
import androidx.compose.material.icons.filled.PermIdentity
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PersonPin
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sebas.interrapidismo.R
import com.sebas.interrapidismo.model.state.HomeState
import com.sebas.interrapidismo.ui.components.LoaderComponent
import com.sebas.interrapidismo.ui.navigation.Localities
import com.sebas.interrapidismo.ui.navigation.Tables

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by homeViewModel.uiState.collectAsState()
    when {
        uiState.isLoading -> LoaderComponent()
    }

    Home(
        uiState,
        {
            navController.navigate(Tables)
        },
        {
            navController.navigate(Localities)
        }
    )
}

@Composable
fun Home(
    uiState: HomeState,
    onNavigateTables: () -> Unit,
    onNavigateLocalities: () -> Unit
) {
    Scaffold {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            HomeContent(
                modifier = Modifier.padding(horizontal = 24.dp),
                uiState,
                onNavigateTables = {
                    onNavigateTables()
                },
                onNavigateLocalities = {
                    onNavigateLocalities()
                }
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier,
    uiState: HomeState,
    onNavigateTables: () -> Unit,
    onNavigateLocalities: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = Icons.Filled.PersonPin,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Filled.Person, contentDescription = null)
            Text(stringResource(id = R.string.text_user, uiState.userInformation.user),style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height( 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Filled.CardTravel, contentDescription = null)
            Text(stringResource(id = R.string.text_identification,uiState.userInformation.identification),style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height( 20.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(imageVector = Icons.Filled.VerifiedUser, contentDescription = null)
            Text(stringResource(id = R.string.text_name,uiState.userInformation.names), style = MaterialTheme.typography.bodyMedium)
        }
        Spacer(modifier = Modifier.height( 20.dp))
        Row {
            Button(onClick = {
                onNavigateTables()
            }, modifier = Modifier
                .weight(1f)
                .padding(5.dp)) {
                Text(stringResource(id = R.string.text_tables))
            }
            Button(onClick = {
                onNavigateLocalities()
            }, modifier = Modifier
                .weight(1f)
                .padding(5.dp)) {
                Text(stringResource(id = R.string.text_localities))
            }
        }
    }
}


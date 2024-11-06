package com.sebas.interrapidismo.ui.features.login

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.sebas.interrapidismo.R
import com.sebas.interrapidismo.model.state.LoginState
import com.sebas.interrapidismo.ui.components.LoaderComponent
import com.sebas.interrapidismo.ui.components.PasswordInputText
import com.sebas.interrapidismo.ui.components.SimpleInputText
import com.sebas.interrapidismo.ui.components.dialogs.AlertDialog
import com.sebas.interrapidismo.ui.navigation.Home

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val activity = (LocalContext.current as? Activity)

    when {
        uiState.errorLogin != null -> {
            AlertDialog(
                title = stringResource(id = R.string.title_error_sign_in),
                message = uiState.errorLogin!!
            ) {
                viewModel.onDismissModal()
            }
        }
        uiState.isLoading -> LoaderComponent()
    }

    when(uiState.versionAppState) {
        VersionAppState.LOWER_VERSION -> {
            AlertDialog(
                title = stringResource(id = R.string.title_version_app),
                message = stringResource(id = R.string.message_lower_version)
            ) {
                viewModel.onDismissModal()
                activity?.finish()
            }
        }
        VersionAppState.UPPER_VERSION -> {
            AlertDialog(
                title = stringResource(id = R.string.title_version_app),
                message = stringResource(id = R.string.message_upper_version)
            ) {
                viewModel.onDismissModal()
                activity?.finish()
            }
        }
        null -> {}
    }
    Login(uiState) {
        navController.navigate(Home)
    }
}

@Composable
fun Login(
    uiState: LoginState,
    onNavigateHome: () -> Unit
) {
    Scaffold {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            LoginContent(modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp),
                uiState
            ) {
                onNavigateHome()
            }
        }
    }
}

@Composable
fun LoginContent(
    modifier: Modifier,
    uiState: LoginState,
    loginViewModel: LoginViewModel = hiltViewModel(),
    onNavigateHome: () -> Unit
) {
    Column(modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.label_sing_in),
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(20.dp))
        SimpleInputText(
            labelInput = stringResource(id = R.string.label_user),
            textValue = uiState.userName
        ) {
            loginViewModel.onUpdateUserName(it)
        }
        Spacer(modifier = Modifier.height(20.dp))
        PasswordInputText(labelInput = stringResource(id = R.string.label_password), textValue = uiState.password) {
            loginViewModel.onUpdatePassword(it)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            loginViewModel.login {
                onNavigateHome()
            }
        }) {
            Text(text = stringResource(id = R.string.text_sing_in))
        }
    }
}
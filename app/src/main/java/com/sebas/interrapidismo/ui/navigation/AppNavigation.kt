package com.sebas.interrapidismo.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.sebas.interrapidismo.ui.features.home.HomeScreen
import com.sebas.interrapidismo.ui.features.localities.LocalitiesScreen
import com.sebas.interrapidismo.ui.features.login.LoginScreen
import com.sebas.interrapidismo.ui.features.tables.TablesInformationScreen
import com.sebas.interrapidismo.ui.features.tables.TablesScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Login
    ) {
        composable<Login> {
            LoginScreen(navController)
        }
        composable<Home> {
            HomeScreen(navController)
        }
        composable<Localities> {
            LocalitiesScreen(navController)
        }
        composable<Tables> {
            TablesScreen(navController)
        }
        composable<TableInformation> { backStackEntry ->
            val tableName = backStackEntry.toRoute<TableInformation>()
            TablesInformationScreen(navController,tableName.nameTable)
        }
    }
}
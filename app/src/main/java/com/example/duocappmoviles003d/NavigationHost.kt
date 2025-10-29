package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                navegarHaciaMain = {
                    navController.navigate(NavigationRoutes.MAIN)
                }
            )
        }

        composable(route = NavigationRoutes.MAIN) {
            MainScreensita(
                navegarHaciaMain = {
                    // Aquí podrías volver al login o navegar a otro lado
                    navController.navigate(NavigationRoutes.LOGIN)
                }
            )
        }

    }
}

package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavigationRoutes.POKEDEX_HOME) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        composable(route = NavigationRoutes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    // vuelta al login para iniciar sesión
                    navController.popBackStack()
                }
            )
        }

        composable(route = NavigationRoutes.POKEDEX_HOME) {
            PokedexHomeScreen()
        }
    }
}
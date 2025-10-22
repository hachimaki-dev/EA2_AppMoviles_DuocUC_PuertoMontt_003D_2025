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
        startDestination = NavigationRoutes.ENTRY
    ) {
        // Ruta de Entrada
        composable(route = NavigationRoutes.ENTRY) {
            PantallaEntrada(
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.LOGIN)
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        // Ruta: Login
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { username ->
                    navController.navigate(NavigationRoutes.createHomeRoute(username)) {
                        popUpTo(NavigationRoutes.ENTRY)
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        // Ruta: Register, ahora llama a Registro
        composable(route = NavigationRoutes.REGISTER) {
            Registro(
                onNavigateToHome = { username ->
                    navController.navigate(NavigationRoutes.createHomeRoute(username)) {
                        popUpTo(NavigationRoutes.ENTRY)
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(navController.graph.startDestinationId)
                    }
                }
            )
        }

        // Ruta: Home
        composable(
            route = NavigationRoutes.HOME,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            HomeScreen(
                username = username,
                onNavigateBack = {
                    navController.popBackStack(NavigationRoutes.ENTRY, inclusive = false)
                }
            )
        }

    }
}
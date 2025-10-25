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

        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { username ->
                    navController.navigate(NavigationRoutes.createHomeRoute(username)) {
                        popUpTo(NavigationRoutes.ENTRY) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        composable(route = NavigationRoutes.REGISTER) {
            Registro(
                onNavigateToHome = { username ->
                    navController.navigate(NavigationRoutes.createHomeRoute(username)) {
                        popUpTo(NavigationRoutes.ENTRY) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.LOGIN)
                }
            )
        }

        composable(
            route = NavigationRoutes.HOME,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            HomeScreen(
                username = username,
                onNavigate = { ruta ->
                    if (ruta == "login") {
                        navController.navigate(NavigationRoutes.LOGIN) { popUpTo(0) }
                    } else {
                        // Navega a la ruta que se le pase (ej: "productos/usuario")
                        navController.navigate(ruta)
                    }
                }
            )
        }

        // ***** CAMBIO: AÑADIDA LA NUEVA RUTA PARA LA PANTALLA DE PRODUCTOS *****
        composable(
            route = NavigationRoutes.PRODUCTS, // Asume que tienes "productos/{username}" en NavigationRoutes
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            PantallaProductos(
                username = username,
                onNavigate = { ruta ->
                    if (ruta == "login") {
                        navController.navigate(NavigationRoutes.LOGIN) { popUpTo(0) }
                    } else {
                        // Navega a la ruta que se le pase (ej: "home")
                        navController.navigate(ruta)
                    }
                }
            )
        }
    }
}
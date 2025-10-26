package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel // ***** AÑADIDO: Import para el ViewModel *****
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun NavigationHost(navController: NavHostController) {
    // ***** AÑADIDO: Se crea una única instancia del ViewModel para toda la app *****
    val cartViewModel: CartViewModel = viewModel()

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
                        navController.navigate(ruta)
                    }
                }
            )
        }

        composable(
            route = NavigationRoutes.PRODUCTS,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            PantallaProductos(
                username = username,
                onNavigate = { ruta ->
                    if (ruta == "login") {
                        navController.navigate(NavigationRoutes.LOGIN) { popUpTo(0) }
                    } else {
                        navController.navigate(ruta)
                    }
                },
                // Se pasa el ViewModel a la pantalla de productos
                cartViewModel = cartViewModel
            )
        }

        composable(
            route = NavigationRoutes.CART,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            PantallaCarrito(
                username = username,
                onNavigate = { ruta ->
                    if (ruta == "login") {
                        navController.navigate(NavigationRoutes.LOGIN) { popUpTo(0) }
                    } else {
                        navController.navigate(ruta)
                    }
                },
                // Se pasa el ViewModel a la pantalla del carrito
                cartViewModel = cartViewModel
            )
        }
    }
}
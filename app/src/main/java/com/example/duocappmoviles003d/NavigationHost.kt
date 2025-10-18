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
        // Login
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { username ->
                    navController.navigate(NavigationRoutes.createHomeRoute(username))
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        // Registro
        composable(route = NavigationRoutes.REGISTER) {
            RegistrarScreen(
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }

        // Catalogo / Home
        composable(
            route = NavigationRoutes.HOME,
            arguments = listOf(navArgument("username") { type = NavType.StringType })
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"
            CatalogoScreen(
                username = username,
                onNavigateToProfile = { navController.navigate(NavigationRoutes.PROFILE) },
                onNavigateBack = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                },
                navegarHaciaCarrito = { navController.navigate(NavigationRoutes.CART) }
            )
        }

        // Perfil
        composable(route = NavigationRoutes.PROFILE) {
            ProfileScreen(onNavigateBack = { navController.popBackStack() })
        }

        // Carrito
        composable(route = NavigationRoutes.CART) {
            VistaCarrito(
                carrito = carritoGlobal,
                navegarHaciaCatalogo = {
                    navController.navigate(NavigationRoutes.HOME) {
                        popUpTo(NavigationRoutes.HOME) { inclusive = true }
                    }
                },
                cerrarSesion = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}

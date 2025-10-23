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
        // Ruta: Login (sin parámetros)
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { username ->
                    navController.navigate(
                        NavigationRoutes.createHomeRoute(username)
                    )
                }
            )
        }

        // Ruta: Home (con parámetro)
        composable(
            route = NavigationRoutes.HOME,
            arguments = listOf(
                navArgument("username") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: "Usuario"

            HomeScreen(
                username = username,
                onNavigateToDetail = {
                    navController.navigate(NavigationRoutes.PROFILE) // O DETAIL
                },
                onNavigateToLogin = {
                    navController.popBackStack(NavigationRoutes.LOGIN, inclusive = true)
                    navController.navigate(NavigationRoutes.LOGIN)
                },

                onNavigateToCart = {
                    navController.navigate(NavigationRoutes.CART)
                }
            )
        }

        composable(route = NavigationRoutes.CART){
            VistaCarrito(
                navegarHaciaCarrito = {
                    navController.navigate(NavigationRoutes.CART)
                }
            )
        }
    }
}
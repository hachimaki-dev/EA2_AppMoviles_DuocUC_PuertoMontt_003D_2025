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
                onNavigateToProfile = {
                    navController.navigate(NavigationRoutes.PROFILE)
                },
                onNavigateBack = {
                    navController.popBackStack()
                },
                navegarHaciaCarrito = {
                    navController.navigate(NavigationRoutes.CART)
                }
            )
        }

        // Ruta: Profile (sin parámetros)
        composable(route = NavigationRoutes.PROFILE) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
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
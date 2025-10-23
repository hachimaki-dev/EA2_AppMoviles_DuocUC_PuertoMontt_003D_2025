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
        // Ruta: Login
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                navegarHaciapokedexScreen = {
                    navController.navigate(NavigationRoutes.PokedexHomeScreen)
                }
            )
        }

        // Ruta: Home Pokedex
        composable(route = NavigationRoutes.PokedexHomeScreen) {
            ShowHomePokedexScreen(
                navegarHaciapokedexScreen = {
                    navController.navigate(NavigationRoutes.PokedexHomeScreen)
                }
            )
        }
    }
}

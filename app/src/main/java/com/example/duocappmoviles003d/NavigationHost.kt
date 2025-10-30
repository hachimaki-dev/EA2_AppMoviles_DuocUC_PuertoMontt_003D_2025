package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {

        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                navegarHaciaMain = {
                    navController.navigate(NavigationRoutes.MAIN) {

                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }


        composable(route = NavigationRoutes.MAIN) {
            MainScreensita(
                navegarHaciaMain = { ruta ->
                    navController.navigate(ruta)
                }
            )
        }



        composable(route = NavigationRoutes.PRODUCT) {

            ProductScreen(navController = navController)
        }

        composable(route = NavigationRoutes.LOCATION) {

            LocationScreen(navController = navController)
        }

        composable(route = NavigationRoutes.PROFILE) {

            ProfileScreen(navController = navController)
        }

        composable(route = NavigationRoutes.CARRITO) {

            CarritoScreen(navController = navController)
        }
    }
}
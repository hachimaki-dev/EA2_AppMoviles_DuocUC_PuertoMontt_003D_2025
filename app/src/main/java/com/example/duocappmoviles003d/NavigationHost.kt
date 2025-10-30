package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
// Añade los imports para tus pantallas si están en otros archivos
// import com.example.duocappmoviles003d.screens.ProductScreen
// import com.example.duocappmoviles003d.screens.LocationScreen
// ... etc

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
            // Aquí llamas a tu pantalla de Productos
            // (Asegúrate de haberla creado en otro archivo)
            ProductScreen(navController = navController)
        }

        composable(route = NavigationRoutes.LOCATION) {
            // Aquí llamas a tu pantalla de Ubicación
            LocationScreen(navController = navController)
        }

        composable(route = NavigationRoutes.PROFILE) {
            // Aquí llamas a tu pantalla de Perfil
            ProfileScreen(navController = navController)
        }

        composable(route = NavigationRoutes.CARRITO) {
            // Aquí llamas a tu pantalla de Carrito
            CarritoScreen(navController = navController)
        }
    }
}
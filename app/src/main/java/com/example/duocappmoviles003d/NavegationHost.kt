package com.example.duocappmoviles003d.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.duocappmoviles003d.ui.view.*

@Composable
fun NavegationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login.route
    ) {
        composable(NavigationRoute.Login.route) {
            LoginScreen(navController)
        }

        composable(NavigationRoute.Registrar.route) {
            RegistrarScreen(navController)
        }

        composable(NavigationRoute.Home.route) {
            CatalogoScreen(navController)
        }

        composable(NavigationRoute.Catalogo.route) {
            CatalogoScreen(navController)
        }

        composable(NavigationRoute.Carrito.route) {
            VistaCarrito(navController)
        }

        composable(NavigationRoute.Perfil.route) {
            ProfileScreen(navController)
        }

        composable(NavigationRoute.ForgotPassword.route) {
            ForgotPassword(navController)
        }
    }
}

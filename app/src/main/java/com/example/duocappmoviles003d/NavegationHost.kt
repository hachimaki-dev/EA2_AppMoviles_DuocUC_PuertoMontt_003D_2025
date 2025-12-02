package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duocappmoviles003d.navigation.NavigationRoute
import com.example.duocappmoviles003d.ui.view.CatalogoScreen
import com.example.duocappmoviles003d.ui.view.ForgotPassword
import com.example.duocappmoviles003d.ui.view.LoginScreen
import com.example.duocappmoviles003d.ui.view.ProfileScreen
import com.example.duocappmoviles003d.ui.view.RegistrarScreen
import com.example.duocappmoviles003d.ui.view.VistaCarrito

@Composable
fun NavigationHost(navController: NavHostController) {

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

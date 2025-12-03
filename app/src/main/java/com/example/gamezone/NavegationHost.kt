package com.example.gamezone

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamezone.navigation.NavigationRoute
import com.example.gamezone.ui.view.CatalogoScreen
import com.example.gamezone.ui.view.ForgotPassword
import com.example.gamezone.ui.view.LoginScreen
import com.example.gamezone.ui.view.ProfileScreen
import com.example.gamezone.ui.view.RegistrarScreen
import com.example.gamezone.ui.view.Carrito
import com.example.gamezone.viewmodel.AuthViewModel
import com.example.gamezone.viewmodel.CarritoViewModel

@Composable
fun NavigationHost(navController: NavHostController) {

    val carritoViewModel: CarritoViewModel = viewModel()
    val authViewModel: AuthViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Login.route
    ) {

        composable(NavigationRoute.Login.route) {
            LoginScreen(navController = navController,
                viewModel = authViewModel)

        }

        composable(NavigationRoute.Registrar.route) {
            RegistrarScreen(navController)
        }

        composable(NavigationRoute.Catalogo.route) {
            CatalogoScreen(
                navController = navController,
                carritoViewModel = carritoViewModel
            )
        }

        composable(NavigationRoute.Carrito.route) {
            Carrito(
                navController = navController,
                viewModel = carritoViewModel
            )
        }

        composable(NavigationRoute.Perfil.route) {
            ProfileScreen(
                navController = navController,
                authViewModel = authViewModel
            )
        }

        composable(NavigationRoute.ForgotPassword.route) {
            ForgotPassword(navController)
        }
    }
}

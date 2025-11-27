package com.example.duocappmoviles003d.view

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duocappmoviles003d.LoginScreen


@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { navController.navigate(NavigationRoutes.CATALOGO) },
                onNavigateToRegister = { navController.navigate(NavigationRoutes.REGISTER) },
                onNavigateToForgot = { navController.navigate(NavigationRoutes.FORGOT) }
            )
        }

        composable(NavigationRoutes.REGISTER) {
            RegistrarScreen(onNavigateToLogin = {
                navController.navigate(NavigationRoutes.LOGIN) {
                    popUpTo(NavigationRoutes.REGISTER) { inclusive = true }
                }
            })
        }

        composable(NavigationRoutes.CATALOGO) {
            CatalogoScreen(
                onNavigateToProfile = { navController.navigate(NavigationRoutes.PROFILE) },
                onNavigateHaciaCarrito = { navController.navigate(NavigationRoutes.CART) },
                onCerrarSesion = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.CATALOGO) { inclusive = true }
                    }
                }
            )
        }

        composable(NavigationRoutes.PROFILE) {
            ProfileScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(NavigationRoutes.CART) {
            VistaCarrito(
                navegarHaciaCatalogo = { navController.navigate(NavigationRoutes.CATALOGO) },
                onPagar = { /* lógica de pago */ }
            )
        }

        composable(NavigationRoutes.FORGOT) {
            ForgotPassword(onNavigateBack = {
                navController.navigate(NavigationRoutes.LOGIN) {
                    popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                }
            })
        }
    }
}

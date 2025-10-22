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

        // --- Pantalla Login ---
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToHome = { username ->
                    UsuarioActivo = usuariosRegistrados.find { it.username == username }
                    navController.navigate(NavigationRoutes.CATALOGO) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                },
                onNavigateToForgot = {
                    navController.navigate(NavigationRoutes.FORGOT)
                }
            )
        }

        // --- Pantalla Registro ---
        composable(NavigationRoutes.REGISTER) {
            RegistrarScreen(
                onNavigateToLogin = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.REGISTER) { inclusive = true }
                    }
                }
            )
        }

        // --- Pantalla Catálogo ---
        composable(NavigationRoutes.CATALOGO) {
            CatalogoScreen(
                onNavigateToProfile = { navController.navigate(NavigationRoutes.PROFILE) },
                onNavigateHaciaCarrito = { navController.navigate(NavigationRoutes.CART) },
                onCerrarSesion = {
                    UsuarioActivo = null
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.CATALOGO) { inclusive = true }
                    }
                }
            )
        }

        // --- Pantalla Perfil ---
        composable(NavigationRoutes.PROFILE) {
            ProfileScreen(onNavigateBack = { navController.popBackStack() })
        }

        // --- Pantalla Carrito ---
        composable(NavigationRoutes.CART) {
            VistaCarrito(
                carrito = carritoGlobal,
                navegarHaciaCatalogo = { navController.navigate(NavigationRoutes.CATALOGO) },
                onPagar = { /* Lógica de pago */ }
            )
        }

        // --- Pantalla Recuperar Contraseña ---
        composable(NavigationRoutes.FORGOT) {
            ForgotPassword(
                onNavigateBack = {
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                }
            )
        }
    }
}

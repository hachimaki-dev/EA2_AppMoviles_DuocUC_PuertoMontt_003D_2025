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
        // 1. Ruta: Login -> Navega a CATALOGUE
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToCatalogue = {
                    navController.navigate(NavigationRoutes.CATALOGUE)
                }
            )
        }

        // 2. Ruta: Catálogo de Productos -> Navega a CART
        composable(route = NavigationRoutes.CATALOGUE) {
            ProductCatalogueScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCart = { navController.navigate(NavigationRoutes.CART) }
            )
        }
// NavigationHost.kt

// ... (rutas 1, 2, 3) ...

// 4. Ruta: Carrito (¡CORREGIDA! Ahora navega a CHECKOUT)
        composable(route = NavigationRoutes.CART) {
            VistaCarrito(
                onNavigateBack = {
                    navController.popBackStack()
                },
                // <--- ¡ESTE PARÁMETRO FALTABA!
                onNavigateToCheckout = {
                    navController.navigate(NavigationRoutes.CHECKOUT)
                }
            )
        }

// ... (rutas 5, 6, 7) ...

        // 4. Ruta: Home (Mantenida, aunque no se use desde Login)
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
                    navController.navigate(NavigationRoutes.PROFILE)
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

        // 5. Ruta: Profile (Mantenida)
        composable(route = NavigationRoutes.PROFILE) {
            ProfileScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}
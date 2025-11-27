package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.duocappmoviles003d.Cart.CartScreen // Importación NUEVA
import com.example.duocappmoviles003d.Login.LoginScreen // Importación NUEVA
import com.example.duocappmoviles003d.ProductCatalogue.ProductCatalogueScreen // Importación NUEVA

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        // 1. Ruta: Login
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onNavigateToCatalogue = {
                    navController.navigate(NavigationRoutes.CATALOGUE)
                }
            )
        }

        // 2. Ruta: Catálogo
        composable(route = NavigationRoutes.CATALOGUE) {
            ProductCatalogueScreen(
                onNavigateBack = { navController.popBackStack() },
                onNavigateToCart = { navController.navigate(NavigationRoutes.CART) }
            )
        }

        // 3. Ruta: Carrito (CORREGIDO)
        composable(route = NavigationRoutes.CART) {
            CartScreen(
                onNavigateBack = {
                    navController.popBackStack()
                },
                onNavigateToCheckout = {
                    // Navega a la pantalla de pago (asegúrate de tener esta ruta definida o creada)
                    navController.navigate(NavigationRoutes.CHECKOUT)
                }
            )
        }

        // 4. Ruta: Checkout (Pago) - Opcional si ya la tienes implementada
        composable(route = NavigationRoutes.CHECKOUT) {
            // Aquí iría tu CheckoutScreen si la tienes creada
            // CheckoutScreen(onPaymentSuccess = { navController.navigate(NavigationRoutes.ORDER_CONFIRMED) })
            // Por ahora dejamos un placeholder si no existe la pantalla aún:
            androidx.compose.material3.Text("Pantalla de Pago en construcción")
        }
    }
}
package com.example.ferremas.screens.home.navigation

sealed class Screens(val route: String) {
    object Login : Screens("login")  // ← minúscula para consistencia
    object SignUp : Screens("signup")  // ← minúscula para consistencia
    object Home : Screens("home")  // ← minúscula para consistencia
    object Cart : Screens("cart")  // ← minúscula para consistencia
    object Profile : Screens("profile")  // ← RUTA NUEVA AGREGADA
    object Categories : Screens("categories")  // ← RUTA NUEVA AGREGADA
    object ProductDetails : Screens("product_details/{productId}") {
        fun createRoute(productId: String) = "product_details/$productId"
    }
}
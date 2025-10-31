package com.example.duocappmoviles003d

object NavigationRoutes {
    const val LOGIN = "login"
    const val HOME = "home/{username}" // {username} es el parámetro

    const val DETAIL = "detail"

    const val CATALOGUE = "catalogue_products"
    const val PROFILE = "profile"
    const val CART = "cart"

    // ¡AÑADIDO! Nuevas rutas de pago
    const val CHECKOUT = "checkout"
    const val ORDER_CONFIRMED = "order_confirmed"

    // Función helper para crear la ruta con parámetro
    fun createHomeRoute(username: String) = "home/$username"
}
package com.example.duocappmoviles003d

object NavigationRoutes {
    const val ENTRY = "entry"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val HOME = "home/{username}"

    const val PRODUCTS = "productos/{username}"
    fun createProductsRoute(username: String) = "productos/$username"

    // Función helper para crear la ruta con parámetro
    fun createHomeRoute(username: String) = "home/$username"
}
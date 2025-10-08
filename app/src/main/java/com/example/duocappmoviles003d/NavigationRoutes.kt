package com.example.duocappmoviles003d

object NavigationRoutes {
    const val LOGIN = "login"
    const val HOME = "home/{username}" // {username} es el parámetro
    const val PROFILE = "profile"

    // Función helper para crear la ruta con parámetro
    fun createHomeRoute(username: String) = "home/$username"
}
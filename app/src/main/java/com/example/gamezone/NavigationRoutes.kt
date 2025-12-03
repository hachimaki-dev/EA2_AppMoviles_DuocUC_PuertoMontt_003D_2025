package com.example.gamezone.navigation

sealed class NavigationRoute(val route: String) {

    object Login : NavigationRoute("login")
    object Registrar : NavigationRoute("registrar")
    object Catalogo : NavigationRoute("catalogo")
    object Carrito : NavigationRoute("carrito")
    object Perfil : NavigationRoute("perfil")
    object ForgotPassword : NavigationRoute("forgot_password")
}

package com.example.duocappmoviles003d

object NavigationRoutes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val FORGOT = "forgotPassword"
    const val CATALOGO = "catalogo/{username}"
    const val PROFILE = "profile"
    const val CART = "cart"

    fun createHomeRoute(username: String) = "catalogo/$username"
}

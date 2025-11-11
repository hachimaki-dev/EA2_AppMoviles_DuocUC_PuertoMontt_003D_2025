package com.example.ferremas.screens.home.navigation

sealed class Screens(val route: String){

    object Cart: Screens("Cart")

    object ProductDetails:Screens("product_details/{productId}")
        fun createRoute(productId: String) = "product_details/$productId"

    object Login: Screens("Login")
    object SignUp: Screens("SignUp")

    object Home: Screens("Home")

}
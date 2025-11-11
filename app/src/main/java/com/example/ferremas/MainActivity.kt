package com.example.ferremas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ferremas.screens.home.HomeScreen
import com.example.ferremas.screens.home.cart.CartScreen
import com.example.ferremas.screens.home.categories.CategoryScreen
import com.example.ferremas.screens.home.navigation.Screens
import com.example.ferremas.screens.home.profile.ProfileScreen
import com.example.ferremas.screens.home.profile.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            // sistema de navegacion
            val navController = rememberNavController()

            // Nav host
            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                // definir rutas usando composables
                // para cada vista que se necesite
                composable(Screens.Home.route){
                    HomeScreen(
                        navController= navController,
                        onProfileClick = { navController.navigate(route = "Profile") },
                        onCartClick = { navController.navigate(route = "Cart") }
                    )
                }
                composable("Cart"){
                    CartScreen(navController= navController)
                }

                composable("Profile"){
                    ProfileScreen(navController = navController,
                        onSingOut = {/** logica de sign out**/})
                }

                composable("Categories"){
                    CategoryScreen(navController)
                }

                composable(Screens.SignUp.route){
                    SignUpScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        },
                        onSignUpSucess = {
                            navController.navigate(Screens.Home.route)
                        }
                    )
                }

            }
        }
    }
}



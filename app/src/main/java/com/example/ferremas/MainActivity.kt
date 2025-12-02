package com.example.ferremas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.ferremas.screens.a.home.HomeScreen
import com.example.ferremas.screens.a.cart.CartScreen
import com.example.ferremas.screens.a.categories.CategoryScreen
import com.example.ferremas.screens.a.navigation.Screens
import com.example.ferremas.screens.a.profile.LoginScreen
import com.example.ferremas.screens.a.profile.ProfileScreen
import com.example.ferremas.screens.a.profile.SignUpScreen
import androidx.activity.viewModels
import com.example.ferremas.viewmodels.UserViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val navController = rememberNavController()
            val userViewModel: UserViewModel by viewModels()

            NavHost(
                navController = navController,
                startDestination = Screens.Login.route  // ← Empieza en login
            ) {
                // PANTALLAS DE AUTENTICACIÓN
                composable(Screens.Login.route) {
                    LoginScreen(
                        userViewModel = userViewModel,
                        onNavigateToSignUp = {
                            navController.navigate(Screens.SignUp.route)
                        },
                        onLoginSuccess = {
                            navController.navigate(Screens.Home.route) {
                                popUpTo(Screens.Login.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Screens.SignUp.route) {
                    SignUpScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        },
                        onSignUpSuccess = {
                            navController.navigate(Screens.Home.route) {
                                popUpTo(Screens.Login.route) { inclusive = true }
                            }
                        }
                    )
                }

                // PANTALLAS PRINCIPALES
                composable(Screens.Home.route) {
                    HomeScreen(
                        navController = navController,
                        onProfileClick = { navController.navigate(Screens.Profile.route) },
                        onCartClick = { navController.navigate(Screens.Cart.route) }
                    )
                }

                composable(Screens.Cart.route) {
                    CartScreen(navController = navController)
                }

                composable(Screens.Profile.route) {
                    ProfileScreen(
                        userViewModel = userViewModel,  // ← ✅ AGREGAR ESTE PARÁMETRO
                        navController = navController,
                        onSingOut = {
                            userViewModel.logout()  // ← ✅ LLAMAR AL LOGOUT DEL VIEWMODEL
                            navController.navigate(Screens.Login.route) {
                                popUpTo(Screens.Home.route) { inclusive = true }
                            }
                        }
                    )
                }

                composable(Screens.Categories.route) {
                    CategoryScreen(navController)
                }
            }
        }
    }
}
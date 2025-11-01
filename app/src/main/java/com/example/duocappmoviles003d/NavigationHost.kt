package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.material3.Text

@Composable
fun NavigationHost(navController: NavHostController, cartViewModel: CartViewModel) {
    val context = LocalContext.current
    val prefs = context.getSharedPreferences("auth_prefs", android.content.Context.MODE_PRIVATE)
    val remembered = prefs.getBoolean("remembered", false)
    val initialRoute = if (remembered) NavigationRoutes.CATALOG else NavigationRoutes.LOGIN

    NavHost(
        navController = navController,
        startDestination = initialRoute
    ) {
        composable(route = NavigationRoutes.CATALOG) {
            CatalogoScreen(
                cartViewModel = cartViewModel,
                navegarHaciaContacto = {
                    navController.navigate(NavigationRoutes.CONTACT)
                },
                navegarHaciaConfiguracion = {
                    navController.navigate(NavigationRoutes.SETTINGS)
                },
                navegarHaciaCarrito = {
                    navController.navigate(NavigationRoutes.CART)
                }
            )
        }

        composable(route = NavigationRoutes.LOGIN) {
            val snackbarHostState = remember { SnackbarHostState() }
            LoginScreen(
                navController = navController,
                preferencias = prefs,
                snackbarHostState = snackbarHostState
            )
        }

        composable(route = NavigationRoutes.CONTACT) {
            ShowContactUi(
                navegarHaciaInicio = {
                    navController.navigate(NavigationRoutes.CATALOG)
                }
            )
        }

        composable(route = NavigationRoutes.SETTINGS) {
            SettingsScreen(
                onLogout = {
                    prefs.edit().putBoolean("remembered", false).apply()
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(0)
                    }
                }
            )
        }

        composable(route = NavigationRoutes.CART) {
            CartScreen(
                cartViewModel = cartViewModel,
                onBack = { navController.navigate(NavigationRoutes.CATALOG) },
                onCheckout = {
                    // Por ahora, mostramos un mensaje simple
                    // En una implementación posterior, se navega a un flujo de pago
                }
            )
        }
    }
}

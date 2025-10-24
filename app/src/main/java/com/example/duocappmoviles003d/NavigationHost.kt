package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost(navController: NavHostController) {
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
                navegarHaciaContacto = {
                    navController.navigate(NavigationRoutes.CONTACT)
                },
                navegarHaciaConfiguracion = {
                    navController.navigate(NavigationRoutes.SETTINGS)
                }
            )
        }

        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavigationRoutes.CATALOG)
                }
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
    }
}

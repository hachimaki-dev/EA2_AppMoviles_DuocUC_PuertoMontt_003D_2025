package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Definimos las rutas de la app
object AppRoutes {
    const val LOGIN_SCREEN = "login"
    const val MAIN_SCREEN = "main"
    const val PROJECTS_SCREEN = "projects"
    const val CREATE_PROJECT_SCREEN = "create_project"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN_SCREEN // Empezamos en el Login
    ) {
        composable(AppRoutes.LOGIN_SCREEN) {
            PantallaLogin(navController = navController)
        }

        composable(AppRoutes.MAIN_SCREEN) {
            PantallaMenuPrincipal(navController = navController)
        }

        composable(AppRoutes.PROJECTS_SCREEN) {
            PantallaProyectos(navController = navController)
        }

        composable(AppRoutes.CREATE_PROJECT_SCREEN) {
            PantallaCrearProyecto(navController = navController)
        }

    }
}
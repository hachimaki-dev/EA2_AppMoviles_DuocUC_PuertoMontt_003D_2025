package com.example.duocappmoviles003d.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.duocappmoviles003d.ui.screens.PantallaCrearProyecto
import com.example.duocappmoviles003d.ui.screens.PantallaLogin
import com.example.duocappmoviles003d.ui.screens.PantallaMain
import com.example.duocappmoviles003d.ui.screens.PantallaProyectos

object AppRoutes {
    const val LOGIN_SCREEN = "login"
    const val MAIN_SCREEN = "main/{email}"
    const val PROJECTS_SCREEN = "projects/{email}"
    const val CREATE_PROJECT_SCREEN = "create_project/{email}"

    fun createMainScreenRoute(email: String) = "main/$email"
    fun createProjectsScreenRoute(email: String) = "projects/$email"
    fun createCreateProjectScreenRoute(email: String) = "create_project/$email"
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN_SCREEN
    ) {
        composable(AppRoutes.LOGIN_SCREEN) {
            PantallaLogin(navController = navController)
        }

        composable(
            route = AppRoutes.MAIN_SCREEN,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: "Usuario"
            PantallaMain(navController = navController, userEmail = email)
        }

        composable(
            route = AppRoutes.PROJECTS_SCREEN,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: "Usuario"
            PantallaProyectos(navController = navController, userEmail = email)
        }

        composable(
            route = AppRoutes.CREATE_PROJECT_SCREEN,
            arguments = listOf(navArgument("email") { type = NavType.StringType })
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: "Usuario"
            PantallaCrearProyecto(navController = navController, userEmail = email)
        }

    }
}
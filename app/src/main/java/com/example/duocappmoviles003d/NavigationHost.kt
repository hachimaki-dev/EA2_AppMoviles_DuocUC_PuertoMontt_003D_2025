package com.example.duocappmoviles003d

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.duocappmoviles003d.viewmodel.PokedexViewModel

@Composable
fun AppNavigationHost(
    navController: NavHostController,
    pokedexViewModel: PokedexViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        // 🔹 AHORA LA APP PARTE EN LOGIN
        startDestination = NavigationRoutes.LOGIN
    ) {
        // ============ LOGIN ============
        composable(route = NavigationRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    // Si el login es correcto, vamos a la Pokédex
                    navController.navigate(NavigationRoutes.POKEDEX_HOME) {
                        // Eliminamos LOGIN del backstack para que no vuelva atrás
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        // ============ REGISTER ============
        composable(route = NavigationRoutes.REGISTER) {
            // Ajusta la firma si tu RegisterScreen usa otros parámetros
            RegisterScreen(
                onRegisterSuccess = {
                    // Tras registrarse, volvemos a LOGIN
                    navController.navigate(NavigationRoutes.LOGIN) {
                        popUpTo(NavigationRoutes.REGISTER) { inclusive = true }
                    }
                },

            )
        }

        // ============ POKÉDEX HOME ============
        composable(route = NavigationRoutes.POKEDEX_HOME) {
            PokedexHomeScreen(
                navController = navController,
                viewModel = pokedexViewModel
            )
        }

        // ============ DETALLE DE POKÉMON ============
        composable(
            route = NavigationRoutes.POKE_DETAIL,
            arguments = listOf(
                navArgument("name") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val name = backStackEntry.arguments?.getString("name") ?: return@composable

            pokedexViewModel.loadPokemonDetail(name)
            val detailState by pokedexViewModel.pokemonDetail.collectAsState()

            detailState?.let { pokemonDetail ->
                PokemonDetailScreen(pokemon = pokemonDetail)
            }
        }
    }
}

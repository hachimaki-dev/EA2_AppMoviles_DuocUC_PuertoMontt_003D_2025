package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.duocappmoviles003d.viewmodel.PokedexViewModel

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.LOGIN
    ) {
        composable(NavigationRoutes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(NavigationRoutes.POKEDEX_HOME) {
                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(NavigationRoutes.REGISTER)
                }
            )
        }

        composable(NavigationRoutes.REGISTER) {
            RegisterScreen(
                onRegisterSuccess = {
                    navController.popBackStack()
                }
            )
        }

        composable(NavigationRoutes.POKEDEX_HOME) {
            PokedexHomeScreen(navController = navController)
        }

        // Pantalla de detalle del Pokémon
        composable(
            route = "${NavigationRoutes.POKE_DETAIL}/{pokemonName}",
            arguments = listOf(navArgument("pokemonName") { type = NavType.StringType })
        ) { backStackEntry ->
            val pokemonName = backStackEntry.arguments?.getString("pokemonName") ?: return@composable
            val viewModel: PokedexViewModel = viewModel()

            // Obtenemos el estado del detalle del Pokémon
            val pokemonDetail by viewModel.pokemonDetail.collectAsState()

            // Llamamos a la API solo una vez por nombre
            LaunchedEffect(pokemonName) {
                viewModel.loadPokemonDetail(pokemonName)
            }

            // Renderizado condicional
            pokemonDetail?.let {
                PokemonDetailScreen(pokemon = it)
            } ?: Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}

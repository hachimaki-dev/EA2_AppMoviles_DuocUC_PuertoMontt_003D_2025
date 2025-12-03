package com.example.ev3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ev3.ui.theme.EV3Theme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import com.example.ev3.ui.navigation.Routes
import com.example.ev3.ui.screens.LoginScreen
import com.example.ev3.ui.screens.SignUpScreen
import com.example.ev3.ui.screens.MainScreen
import com.example.ev3.ui.screens.ContactoScreen
import com.example.ev3.ui.screens.NosotrosScreen
import com.example.ev3.ui.screens.DigimonDetailScreen
import com.example.ev3.data.auth.AuthRepository
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.ev3.utils.MusicManager

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inicializar música de fondo
        MusicManager.initialize(this, R.raw.brave_heart_digimon)

        setContent {
            EV3Theme {
                val navController = rememberNavController()
                val auth = AuthRepository(this)
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Routes.LOGIN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Routes.LOGIN) {
                            LoginScreen(
                                authRepository = auth,
                                onLoginSuccess = { navController.navigate(Routes.MAIN) },
                                onGoToSignUp = { navController.navigate(Routes.SIGN_UP) }
                            )
                        }
                        composable(Routes.SIGN_UP) {
                            SignUpScreen(
                                authRepository = auth,
                                onRegistered = { navController.popBackStack(); navController.navigate(Routes.LOGIN) },
                                onBackToLogin = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.MAIN) {
                            MainScreen(
                                onOpenContacto = { navController.navigate(Routes.CONTACTO) },
                                onOpenNosotros = { navController.navigate(Routes.NOSOTROS) },
                                onDigimonClick = { id -> navController.navigate(Routes.digimonDetail(id)) }
                            )
                        }
                        composable(
                            route = Routes.DIGIMON_DETAIL,
                            arguments = listOf(navArgument("digimonId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val digimonId = backStackEntry.arguments?.getInt("digimonId") ?: 0
                            DigimonDetailScreen(
                                digimonId = digimonId,
                                onBackClick = { navController.popBackStack() }
                            )
                        }
                        composable(Routes.CONTACTO) {
                            ContactoScreen(onBackClick = { navController.popBackStack() })
                        }
                        composable(Routes.NOSOTROS) {
                            NosotrosScreen(onBackClick = { navController.popBackStack() })
                        }
                    }
                }
            }
        }
    }

    override fun onPause() {
        super.onPause()
        MusicManager.onPause()
    }

    override fun onResume() {
        super.onResume()
        MusicManager.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        MusicManager.release()
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EV3Theme {
        // Preview vacío
    }
}

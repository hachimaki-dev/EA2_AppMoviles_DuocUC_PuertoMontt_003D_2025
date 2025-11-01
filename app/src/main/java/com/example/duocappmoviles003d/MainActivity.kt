package com.example.duocappmoviles003d

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    // Creamos el NavController
                    val navController = rememberNavController()
                    // ViewModel del carrito compartido
                    val cartViewModel: CartViewModel = viewModel()

                    // Iniciamos el NavigationHost compartiendo el estado del carrito
                    NavigationHost(navController = navController, cartViewModel = cartViewModel)
                }
            }
        }
    }
}
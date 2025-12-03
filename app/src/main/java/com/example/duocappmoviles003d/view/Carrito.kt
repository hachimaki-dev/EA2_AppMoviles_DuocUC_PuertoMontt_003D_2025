package com.example.duocappmoviles003d.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.duocappmoviles003d.navigation.NavigationRoute
import com.example.duocappmoviles003d.viewmodel.CarritoViewModel
import com.example.duocappmoviles003d.model.Producto

@Composable
fun Carrito(
    navController: NavHostController,
    viewModel: CarritoViewModel = viewModel()
) {
    // collectAsState necesita un valor inicial
    val carrito by viewModel.carrito.collectAsState(initial = emptyList())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Carrito de Compras",
            color = Color.Black,
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.isEmpty()) {

            Text("Tu carrito está vacío", color = Color.Gray)

        } else {

            carrito.forEach { producto: Producto ->

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(producto.nombre, color = Color.Black)

                    Button(
                        onClick = { viewModel.eliminarDelCarrito(producto) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                    ) {
                        Text("Eliminar", color = Color.White)
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // Aquí iría la pantalla de pago si la tienes
                    // navController.navigate(NavigationRoute.Pago.route)
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02B2BF))
            ) {
                Text("Pagar", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                navController.navigate(NavigationRoute.Catalogo.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Volver al catálogo", color = Color.White)
        }
    }
}

package com.example.duocappmoviles003d.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duocappmoviles003d.viewModel.CarritoViewModel

@Composable
fun VistaCarrito(
    navegarHaciaCatalogo: () -> Unit,
    onPagar: () -> Unit,
    viewModel: CarritoViewModel = viewModel()
) {
    val carrito = viewModel.carrito.collectAsState()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Carrito de Compras", color = Color.Black, style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.value.isEmpty()) {
            Text("Tu carrito está vacío", color = Color.Gray)
        } else {
            carrito.value.forEach { producto ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
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
                onClick = onPagar,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191))
            ) {
                Text("Pagar", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = navegarHaciaCatalogo,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Volver al catálogo", color = Color.White)
        }
    }
}

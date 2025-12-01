package com.example.duocappmoviles003d.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duocappmoviles003d.viewmodel.CatalogoViewModel
import com.example.duocappmoviles003d.viewmodel.CarritoViewModel
import com.example.duocappmoviles003d.model.Producto

@Composable
fun CatalogoScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateHaciaCarrito: () -> Unit,
    onCerrarSesion: () -> Unit,
    catalogoViewModel: CatalogoViewModel = viewModel(),
    carritoViewModel: CarritoViewModel = viewModel()
) {
    val productos by catalogoViewModel.productos.collectAsState(initial = emptyList())
    val carrito by carritoViewModel.carrito.collectAsState(initial = emptyList())

    // Cargar productos al entrar
    LaunchedEffect(Unit) {
        catalogoViewModel.cargarProductos()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Catálogo",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        productos.forEach { producto: Producto ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = CardDefaults.cardColors(containerColor = Color(245, 245, 245))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(producto.nombre, color = Color.Black)
                    Button(
                        onClick = { carritoViewModel.agregarAlCarrito(producto) },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191))
                    ) {
                        Text("Agregar", color = Color.Black)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onNavigateHaciaCarrito,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Ver Carrito (${carrito.size})", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onNavigateToProfile,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191))
        ) {
            Text("Perfil", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onCerrarSesion,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Cerrar sesión", color = Color.White)
        }
    }
}

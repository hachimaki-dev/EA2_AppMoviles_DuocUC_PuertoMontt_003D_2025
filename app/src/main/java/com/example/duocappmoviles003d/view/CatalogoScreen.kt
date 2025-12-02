package com.example.duocappmoviles003d.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.duocappmoviles003d.navigation.NavigationRoute
import com.example.duocappmoviles003d.viewmodel.CatalogoViewModel
import com.example.duocappmoviles003d.viewmodel.CarritoViewModel
import com.example.duocappmoviles003d.model.Producto

@Composable
fun CatalogoScreen(
    navController: NavHostController,
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
            "Catálogo",
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
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02B2BF))
                    ) {
                        Text("Agregar", color = Color.Black)
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // 🔵 Ir al carrito
        Button(
            onClick = {
                navController.navigate(NavigationRoute.Carrito.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text("Ver Carrito (${carrito.size})", color = Color.White)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔵 Ir al perfil
        Button(
            onClick = {
                navController.navigate(NavigationRoute.Perfil.route)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02B2BF))
        ) {
            Text("Perfil", color = Color.Black)
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 🔵 Cerrar sesión
        Button(
            onClick = {
                navController.navigate(NavigationRoute.Login.route) {
                    popUpTo(NavigationRoute.Catalogo.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
        ) {
            Text("Cerrar sesión", color = Color.White)
        }
    }
}

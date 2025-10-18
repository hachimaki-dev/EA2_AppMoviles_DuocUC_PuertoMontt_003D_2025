package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
fun VistaCarrito(
    carrito: MutableList<Juego>,
    navegarHaciaCatalogo: () -> Unit,
    cerrarSesion: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope() // Coroutine para mostrar snackbar

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Carrito de Compras",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.isEmpty()) {
            Text("Tu carrito está vacío", fontSize = 16.sp)
        } else {
            LazyColumn {
                items(carrito) { juego ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(2, 178, 191)),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column {
                                Text(juego.nombre, color = Color.Black, fontSize = 16.sp)
                                Text(juego.precio, color = Color.Black, fontSize = 14.sp)
                            }

                            Button(
                                onClick = {
                                    carrito.remove(juego)
                                    // Mostrar Snackbar al eliminar
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "${juego.nombre} eliminado del carrito"
                                        )
                                    }
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text("Eliminar", color = Color.Black, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = navegarHaciaCatalogo,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191))
            ) {
                Text("Seguir Comprando", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = cerrarSesion,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191))
            ) {
                Text("Cerrar Sesión", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(8.dp)
        )
    }
}



package com.example.duocappmoviles003d

import androidx.compose.foundation.background
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

@Composable
fun VistaCarrito(
    carrito: MutableList<Juego>,
    navegarHaciaCatalogo: () -> Unit,
    onPagar: () -> Unit
) {
    // Creamos un estado observable para la lista
    val carritoState = remember { mutableStateListOf<Juego>().apply { addAll(carrito) } }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            Text("Carrito de Compras", color = Color.Black, fontSize = 24.sp)
            Spacer(modifier = Modifier.height(16.dp))

            if (carritoState.isEmpty()) {
                Text("Tu carrito está vacío", color = Color.Black)
            } else {
                LazyColumn {
                    items(carritoState) { juego ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(2,178,191).copy(alpha = 0.1f))
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column {
                                    Text(juego.nombre, color = Color.Black, fontSize = 18.sp)
                                    Text("$${juego.precio} CLP", color = Color.Black)
                                }

                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    // Botón para disminuir cantidad
                                    Button(
                                        onClick = {
                                            if (juego.cantidadState > 1) juego.cantidadState--
                                            else carritoState.remove(juego)
                                        },
                                        modifier = Modifier.size(30.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                                        contentPadding = PaddingValues(0.dp)
                                    ) { Text("-", color = Color.White) }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    Text("${juego.cantidadState}", color = Color.Black)

                                    Spacer(modifier = Modifier.width(8.dp))

                                    // Botón para aumentar cantidad
                                    Button(
                                        onClick = { juego.cantidadState++ },
                                        modifier = Modifier.size(30.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                                        contentPadding = PaddingValues(0.dp)
                                    ) { Text("+", color = Color.White) }

                                    Spacer(modifier = Modifier.width(8.dp))

                                    // Botón eliminar
                                    Button(
                                        onClick = { carritoState.remove(juego) },
                                        modifier = Modifier.height(30.dp),
                                        colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
                                    ) { Text("Eliminar", color = Color.White, fontSize = 12.sp) }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Button(
                    onClick = navegarHaciaCatalogo,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191)),
                    modifier = Modifier.weight(1f)
                ) { Text("Volver al Catálogo", color = Color.Black) }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onPagar,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) { Text("Pagar", color = Color.White) }
            }
        }
    }
}

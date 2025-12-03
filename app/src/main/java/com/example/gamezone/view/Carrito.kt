package com.example.gamezone.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.gamezone.navigation.NavigationRoute
import com.example.gamezone.viewmodel.CarritoViewModel
import kotlin.random.Random

@Composable
fun Carrito(
    navController: NavHostController,
    viewModel: CarritoViewModel = viewModel()
) {
    val carrito by viewModel.carrito.collectAsState(initial = emptyList())
    val total = carrito.sumOf { it.precio }

    // Estado para controlar si se muestra la factura
    var mostrarFactura by remember { mutableStateOf(false) }
    // Número de factura aleatorio para que parezca real
    val numeroFactura = remember { Random.nextInt(100000, 999999) }

    // --- DIÁLOGO DE FACTURA FALSA ---
    if (mostrarFactura) {
        AlertDialog(
            onDismissRequest = {
                // Si hace clic fuera, no hacemos nada o cerramos
            },
            title = {
                Text(text = "¡Pago Exitoso!", fontWeight = FontWeight.Bold, color = Color(0xFF02B2BF))
            },
            text = {
                Column {
                    Text("Comprobante de Pago Electrónico")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Factura N°: $numeroFactura", fontWeight = FontWeight.Bold)
                    Text("Total Pagado: $$total")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Gracias por tu compra en Gamezone.", fontSize = 12.sp, color = Color.Gray)
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarFactura = false // Cerramos el diálogo
                        viewModel.limpiarCarrito() // Limpiamos el carrito
                        // Volvemos al catálogo y borramos el historial para no volver al carrito con "atrás"
                        navController.navigate(NavigationRoute.Catalogo.route) {
                            popUpTo(NavigationRoute.Catalogo.route) { inclusive = true }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text("Aceptar y Volver")
                }
            },
            containerColor = Color.White,
            shape = RoundedCornerShape(16.dp)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Carrito de Compras",
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (carrito.isEmpty()) {
            Box(
                modifier = Modifier.weight(1f).fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text("Tu carrito está vacío", color = Color.Gray, fontSize = 18.sp)
            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(carrito) { producto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            AsyncImage(
                                model = producto.imagen,
                                contentDescription = producto.nombre,
                                modifier = Modifier
                                    .size(70.dp)
                                    .clip(RoundedCornerShape(8.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.width(16.dp))

                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = producto.nombre,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black,
                                    fontSize = 16.sp
                                )
                                Text(
                                    text = "$${producto.precio}",
                                    color = Color(0xFF02B2BF),
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 15.sp
                                )
                            }

                            IconButton(
                                onClick = { viewModel.eliminarDelCarrito(producto) }
                            ) {
                                Text("❌", fontSize = 14.sp)
                            }
                        }
                    }
                }
            }

            Divider(color = Color.LightGray, thickness = 1.dp)
            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Total a pagar:", fontSize = 18.sp, color = Color.Gray)
                Text("$$total", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // BOTÓN PAGAR (Activa el Pop-up)
            Button(
                onClick = {
                    mostrarFactura = true
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF02B2BF)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Pagar $$total", color = Color.Black, fontSize = 18.sp, fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(NavigationRoute.Catalogo.route)
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Seguir comprando", color = Color.Gray)
        }
    }
}
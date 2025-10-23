package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CatalogoScreen(
    onNavigateToProfile: () -> Unit,
    onNavigateHaciaCarrito: () -> Unit,
    onCerrarSesion: () -> Unit
) {
    val username = UsuarioActivo?.username ?: "Usuario"
    var busqueda by remember { mutableStateOf(TextFieldValue("")) }

    Box(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

            // --- logo y perfil ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.game),
                    contentDescription = "Logo",
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.Fit
                )

                Text("Bienvenido, $username", color = Color.Black, fontSize = 18.sp)

                Button(
                    onClick = onNavigateToProfile,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191))
                ) { Text("Perfil", color = Color.Black) }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Buscador ---
            OutlinedTextField(
                value = busqueda,
                onValueChange = { busqueda = it },
                label = { Text("Buscar juego") },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            // --- Lista de juegos filtrada ---
            val juegosFiltrados = catalogoItems.filter {
                it.nombre.contains(busqueda.text, ignoreCase = true)
            }

            LazyColumn {
                items(juegosFiltrados) { juego ->
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
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = juego.imagen),
                                    contentDescription = juego.nombre,
                                    modifier = Modifier.size(60.dp),
                                    contentScale = ContentScale.Crop
                                )

                                Spacer(modifier = Modifier.width(16.dp))

                                Column {
                                    Text(juego.nombre, color = Color.Black, fontSize = 18.sp)
                                    Text("$${juego.precio} CLP", color = Color.Black)
                                }
                            }

                            Row {
                                Button(
                                    onClick = {
                                        agregarAlCarrito(juego)
                                    },
                                    modifier = Modifier.size(45.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                                    contentPadding = PaddingValues(0.dp)
                                ) { Text("+", color = Color.White) }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // --- Footer con Cerrar Sesión y Carrito ---
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = onCerrarSesion,
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                    modifier = Modifier.weight(1f)
                ) { Text("Cerrar Sesión", color = Color.White) }

                Spacer(modifier = Modifier.width(8.dp))

                Button(
                    onClick = onNavigateHaciaCarrito,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191)),
                    modifier = Modifier.weight(1f)
                ) { Text("Carrito", color = Color.Black) }
            }
        }
    }
}

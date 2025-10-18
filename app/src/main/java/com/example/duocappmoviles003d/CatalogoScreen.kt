package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

// Definimos la data class Juego
data class Juego(
    val nombre: String,
    val precio: String
)

// Carrito global compartido entre pantallas
val carritoGlobal = mutableStateListOf<Juego>()

@Composable
fun CatalogoScreen(
    username: String,
    onNavigateToProfile: () -> Unit,
    onNavigateBack: () -> Unit,
    navegarHaciaCarrito: () -> Unit
) {
    var busqueda by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope() // <-- CORRECCIÓN: CoroutineScope para Snackbar

    // Lista simulada de juegos con precios en pesos chilenos
    val juegos = listOf(
        Juego("The Legend of Zelda", "$59.990 CLP"),
        Juego("Super Mario Odyssey", "$49.990 CLP"),
        Juego("Minecraft", "$19.990 CLP"),
        Juego("Cyberpunk 2077", "$39.990 CLP"),
        Juego("Among Us", "$4.990 CLP"),
        Juego("Elden Ring", "$59.990 CLP")
    )

    // Filtrar juegos por búsqueda
    val juegosFiltrados = juegos.filter {
        it.nombre.contains(busqueda.text, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        // Logo y bienvenida
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.game),
                contentDescription = "Logo App",
                modifier = Modifier.size(40.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Bienvenido, $username!",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Barra de búsqueda
        OutlinedTextField(
            value = busqueda,
            onValueChange = { busqueda = it },
            label = { Text("Buscar juego") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color(2,178,191),
                focusedLabelColor = Color(2,178,191),
                unfocusedLabelColor = Color(2,178,191)
            ),
            shape = RoundedCornerShape(26.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Lista de juegos
        LazyColumn(
            modifier = Modifier.fillMaxHeight(0.7f)
        ) {
            items(juegosFiltrados) { juego ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .clickable { /* Detalle juego si quieres */ },
                    colors = CardDefaults.cardColors(containerColor = Color(2,178,191)),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = juego.nombre, color = Color.Black, fontSize = 16.sp)
                        Row {
                            Text(text = juego.precio, color = Color.Black, fontSize = 16.sp)
                            Spacer(modifier = Modifier.width(8.dp))
                            Button(
                                onClick = {
                                    carritoGlobal.add(juego)
                                    // Mostrar Snackbar CORRECTAMENTE
                                    scope.launch {
                                        snackbarHostState.showSnackbar(
                                            "${juego.nombre} agregado al carrito"
                                        )
                                    }
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                            ) {
                                Text("Agregar", color = Color.Black, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Botones inferiores
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = onNavigateToProfile,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191))
            ) {
                Text("Perfil", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = navegarHaciaCarrito,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(26.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191))
            ) {
                Text("Carrito", color = Color.Black)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedButton(
            onClick = onNavigateBack,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
        ) {
            Text("Cerrar Sesion", color = Color(2,178,191))
        }

        // Snackbar
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(8.dp)
        )
    }
}

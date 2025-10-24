package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material.icons.Icons
// 1. Se cambió la importación para usar la versión AutoMirrored
import androidx.compose.material.icons.automirrored.filled.ArrowBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowContactUi(navegarHaciaInicio: () -> Unit) {
    val tomatoRed = Color(0xFFD32F2F)
    val tomatoLight = Color(0xFFFFEBEE)

    var nombre by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    var enviado by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tomatito - Contacto", fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = { navegarHaciaInicio() }) {
                        // 2. Se actualizó el ícono a la versión AutoMirrored
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver al inicio")
                    }
                }
            )
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tomaco),
                contentDescription = "Imagen de fondo de la APP",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // ... (el resto de tu código permanece igual)
                Surface(color = tomatoLight, tonalElevation = 0.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            "Hablemos de tomates frescos",
                            style = MaterialTheme.typography.titleLarge,
                            color = tomatoRed
                        )
                        Text("¿Tienes dudas sobre nuestros productos o pedidos? Escríbenos.")
                    }
                }

                Card {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                        Text("Correo: contacto@tomatito.app", fontWeight = FontWeight.Medium)
                        Text("Teléfono: +56 9 1234 5678", fontWeight = FontWeight.Medium)
                        Text("Dirección: Av. Tomate 123, Puerto Montt", fontWeight = FontWeight.Medium)
                        Text("Horario: Lun–Vie 9:00–18:00", fontWeight = FontWeight.Medium)
                    }
                }

                Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.9F))) {
                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        OutlinedTextField(
                            value = nombre,
                            onValueChange = { nombre = it },
                            label = { Text("Nombre") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = correo,
                            onValueChange = { correo = it },
                            label = { Text("Correo") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.White
                            )
                        )

                        OutlinedTextField(
                            value = mensaje,
                            onValueChange = { mensaje = it },
                            label = { Text("Mensaje") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(min = 120.dp),
                            maxLines = 6,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = Color.White,
                                unfocusedContainerColor = Color.White,
                                disabledContainerColor = Color.White
                            )
                        )

                        Button(
                            onClick = {
                                if (nombre.isNotBlank() && correo.isNotBlank() && mensaje.isNotBlank()) {
                                    enviado = true
                                }
                            },
                            enabled = nombre.isNotBlank() && correo.isNotBlank() && mensaje.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = tomatoRed)
                        ) {
                            Text("Enviar")
                        }
                    }
                }

                if (enviado) {
                    Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92F))) {
                        Column(modifier = Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Text(
                                "¡Gracias! Te contactaremos pronto.",
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            AssistChip(onClick = { navegarHaciaInicio() }, label = { Text("Volver al inicio") })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
private fun ContactoPreview() {
    ShowContactUi { }
}
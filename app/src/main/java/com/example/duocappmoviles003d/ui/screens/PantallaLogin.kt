package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.navigation.AppRoutes
import com.example.duocappmoviles003d.ui.theme.AzulOscuroCITT
import com.example.duocappmoviles003d.ui.theme.TextoPistaCITT
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT

@Composable
fun PantallaLogin(navController: NavController) {

    val usuariosRegistrados = mapOf(
        "estudiante@duocuc.cl" to "duoc123",
        "profesor@profesor.duocuc.cl" to "profe123"
    )

    var rutOCorreo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    var mostrarErrorDialog by remember { mutableStateOf(false) }
    var mensajeError by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            AzulOscuroCITT,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(vertical = 24.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "CITT Connect",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Plataforma de Proyectos y Tracks",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }

                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Inicia Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    OutlinedTextField(
                        value = rutOCorreo,
                        onValueChange = { rutOCorreo = it },
                        label = { Text("Email") },
                        placeholder = { Text("Ej: correo@duocuc.cl") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = TurquesaCITT,
                            focusedLabelColor = TurquesaCITT,
                            unfocusedPlaceholderColor = TextoPistaCITT,
                            unfocusedLabelColor = TextoPistaCITT
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        placeholder = { Text("Ingresa tu clave") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = TurquesaCITT,
                            focusedLabelColor = TurquesaCITT,
                            unfocusedPlaceholderColor = TextoPistaCITT,
                            unfocusedLabelColor = TextoPistaCITT
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    Button(
                        onClick = {
                            val email = rutOCorreo.trim()
                            val pass = clave.trim()

                            if (email.isBlank() || pass.isBlank()) {
                                mensajeError = "Por favor, ingresa email y contraseña."
                                mostrarErrorDialog = true
                            }
                            else if (!email.endsWith("@duocuc.cl") && !email.endsWith("@profesor.duocuc.cl")) {
                                mensajeError = "El correo debe ser de dominio @duocuc.cl o @profesor.duocuc.cl"
                                mostrarErrorDialog = true
                            }
                            else if (usuariosRegistrados.containsKey(email) && usuariosRegistrados[email] == pass) {
                                navController.navigate(AppRoutes.createMainScreenRoute(email)) {
                                    popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                                }
                            }
                            else {
                                mensajeError = "Email o contraseña incorrectos."
                                mostrarErrorDialog = true
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TurquesaCITT,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Acceder a la Plataforma",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    TextButton(onClick = { }) {
                        Text(
                            text = "¿Olvidaste tu Contraseña?",
                            color = TurquesaCITT,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        if (mostrarErrorDialog) {
            AlertDialog(
                onDismissRequest = { mostrarErrorDialog = false },
                title = { Text("Error de Inicio de Sesión") },
                text = { Text(mensajeError) },
                confirmButton = {
                    TextButton(
                        onClick = { mostrarErrorDialog = false }
                    ) {
                        Text("Entendido")
                    }
                }
            )
        }
    }
}
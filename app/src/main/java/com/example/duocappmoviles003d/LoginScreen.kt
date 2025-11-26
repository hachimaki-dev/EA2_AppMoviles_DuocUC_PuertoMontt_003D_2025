package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

// Paleta de Colores
val PrimaryRed = Color(0xFF222222) // Rojo de acento
val DarkBackground = Color(0xFF1E1E1E) // Fondo oscuro para contraste
val CardBackground = DarkBackground.copy(alpha = 0.8f) // Tarjeta semi-transparente

@Composable
fun LoginScreen(
    onNavigateToCatalogue: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var formSubmitted by remember { mutableStateOf(false) }

    val isUsernameInvalid = username.isBlank()
    val isEmailInvalid = email.isBlank() || !email.contains("@")
    val isPasswordInvalid = password.isBlank() || password.length > 4

    fun validateFormAndNavigate() {
        formSubmitted = true
        if (!isUsernameInvalid && !isEmailInvalid && !isPasswordInvalid) {
            onNavigateToCatalogue()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.cd31minutos),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Card(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth(0.9f),
            colors = CardDefaults.cardColors(containerColor = CardBackground),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "DISCO ZONE",
                    style = MaterialTheme.typography.headlineLarge,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Campos de texto con estilo
                OutlinedTextField(
                    value = username, onValueChange = { username = it }, label = { Text("Usuario") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    isError = formSubmitted && isUsernameInvalid,
                    singleLine = true, modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = PrimaryRed, unfocusedBorderColor = Color.Gray,
                        errorBorderColor = PrimaryRed
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = email, onValueChange = { email = it }, label = { Text("Email") },
                    leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = null) },
                    isError = formSubmitted && isEmailInvalid,
                    singleLine = true, modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        // ... (Colores similares a arriba)
                        focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = PrimaryRed, unfocusedBorderColor = Color.Gray,
                        errorBorderColor = PrimaryRed
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password, onValueChange = { password = it }, label = { Text("Contraseña") },
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    isError = formSubmitted && isPasswordInvalid,
                    singleLine = true, visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        // ... (Colores similares a arriba)
                        focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                        focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = PrimaryRed, unfocusedBorderColor = Color.Gray,
                        errorBorderColor = PrimaryRed
                    )
                )

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = { validateFormAndNavigate() },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed)
                ) {
                    Text("INGRESAR AL CATÁLOGO", fontWeight = FontWeight.SemiBold)
                }
            }
        }
    }
}
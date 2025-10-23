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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp



val PrimaryRed = Color(0xFFB71C1C)
@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit
) {

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") } // Nuevo campo
    var password by remember { mutableStateOf("") } // Nuevo campo


    var formSubmitted by remember { mutableStateOf(false) }


    val isUsernameInvalid = username.isBlank()
    val isEmailInvalid = email.isBlank() || !email.contains("@")
    val isPasswordInvalid = password.isBlank() || password.length < 6

    fun validateFormAndNavigate() {
        formSubmitted = true // Intentamos enviar, activar visualización de errores

        if (!isUsernameInvalid && !isEmailInvalid && !isPasswordInvalid) {
            onNavigateToHome(username)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.cd31minutos),
            contentDescription = "Imagen de fondo de mi app",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Gamezone",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White, // Cambiamos a blanco para que se vea sobre el fondo oscuro
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(32.dp))


            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                leadingIcon = { Icon(Icons.Default.Person, contentDescription = null, tint = Color.White) },
                isError = formSubmitted && isUsernameInvalid,
                supportingText = { if (formSubmitted && isUsernameInvalid) Text("El usuario es requerido") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                    focusedBorderColor = PrimaryRed, unfocusedBorderColor = PrimaryRed
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // -- CAMPO 2: EMAIL (Nuevo) --
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Correo Electrónico") },
                leadingIcon = { Icon(Icons.Default.MailOutline, contentDescription = null, tint = Color.White) },
                isError = formSubmitted && isEmailInvalid,
                supportingText = { if (formSubmitted && isEmailInvalid) Text("Formato de correo inválido (ej: a@b.cl)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                    focusedBorderColor = PrimaryRed, unfocusedBorderColor = PrimaryRed
                )
            )

            Spacer(modifier = Modifier.height(8.dp))


            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
                leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null, tint = Color.White) },
                isError = formSubmitted && isPasswordInvalid,
                supportingText = { if (formSubmitted && isPasswordInvalid) Text("Mínimo 6 caracteres") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White, unfocusedTextColor = Color.White,
                    focusedLabelColor = Color.White, unfocusedLabelColor = Color.Gray,
                    focusedBorderColor = PrimaryRed, unfocusedBorderColor = PrimaryRed
                )
            )

            Spacer(modifier = Modifier.height(24.dp))


            Button(
                onClick = { validateFormAndNavigate() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed, contentColor = Color.White)
            ) {
                Text("Ingresar al Home")
            }
        }
    }
}

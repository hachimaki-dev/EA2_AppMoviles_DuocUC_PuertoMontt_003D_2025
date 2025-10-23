package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgot: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.game),
                contentDescription = "Logo de mi app",
                modifier = Modifier
                    .height(120.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Iniciar Sesion",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(2, 178, 191),
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color(2, 178, 191),
                    unfocusedLabelColor = Color(2, 178, 191),
                    disabledBorderColor = Color(2, 178, 191),
                    focusedLabelColor = Color(2, 178, 191),
                    focusedTextColor = Color.Black


                )

            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contrasena") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(2, 178, 191),
                    unfocusedTextColor = Color.Black,
                    unfocusedBorderColor = Color(2, 178, 191),
                    unfocusedLabelColor = Color(2, 178, 191),
                    disabledBorderColor = Color(2, 178, 191),
                    focusedLabelColor = Color(2, 178, 191),
                    focusedTextColor = Color.Black


                )
            )

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = Color.Red,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (username.text.isBlank() || password.text.isBlank()) {
                        errorMessage = "Por favor completa todos los campos"
                    } else if (!validarLogin(username.text, password.text)) {
                        errorMessage = "Usuario o contraseña incorrectos"
                    } else {
                        errorMessage = ""
                        onNavigateToHome(username.text)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191)),
                shape = RoundedCornerShape(26.dp)
            ) { Text("Iniciar Sesion", color = Color.Black, fontWeight = FontWeight.Black) }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onNavigateToRegister() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(26.dp)
            ) { Text("Registrarse", color = Color.White, fontWeight = FontWeight.Black) }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { onNavigateToForgot() },
                modifier = Modifier.fillMaxWidth()
            ) { Text("Olvide mi contrasena", color = Color(2, 178, 191), fontWeight = FontWeight.Black) }
        }
    }
}

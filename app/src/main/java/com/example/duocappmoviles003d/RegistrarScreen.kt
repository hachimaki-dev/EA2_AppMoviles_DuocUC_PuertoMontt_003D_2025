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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegistrarScreen(
    onNavigateToLogin: () -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }
    var confirmPassword by remember { mutableStateOf(TextFieldValue("")) }
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
                    .height(250.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Registrarse",
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

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
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

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Contraseña") },
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

            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = confirmPassword,
                onValueChange = { confirmPassword = it },
                label = { Text("Confirmar Contraseña") },
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

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    errorMessage = ""
                    if (username.text.isBlank() || email.text.isBlank() || password.text.isBlank() || confirmPassword.text.isBlank()) {
                        errorMessage = "Por favor completa todos los campos"
                    } else if (password.text != confirmPassword.text) {
                        errorMessage = "Las contraseñas no coinciden"
                    } else {
                        val nuevoUsuario = Usuario(username.text, email.text, password.text)
                        if (registrarUsuario(nuevoUsuario)) onNavigateToLogin()
                        else errorMessage = "El usuario ya existe"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191)),
                shape = RoundedCornerShape(26.dp)
            ) { Text("Registrar", color = Color.Black, fontWeight = FontWeight.Black) }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                onClick = { onNavigateToLogin() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(26.dp)
            ) { Text("Volver al Login", color = Color.White, fontWeight = FontWeight.Black) }
        }
    }
}

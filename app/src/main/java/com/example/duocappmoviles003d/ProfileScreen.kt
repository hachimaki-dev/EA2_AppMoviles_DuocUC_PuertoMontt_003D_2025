package com.example.duocappmoviles003d

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(
    onNavigateBack: () -> Unit
) {
    val usuario = UsuarioActivo
    var username by remember { mutableStateOf(TextFieldValue(usuario?.username ?: "")) }
    var email by remember { mutableStateOf(TextFieldValue(usuario?.email ?: "")) }
    var mensaje by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Perfil de Usuario",
                fontSize = 24.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Username
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username") },
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

            // Email
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

            Spacer(modifier = Modifier.height(16.dp))

            if (mensaje.isNotEmpty()) {
                Text(text = mensaje, color = Color.Black, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Botón Guardar cambios
            Button(
                onClick = {
                    if (username.text.isBlank() || email.text.isBlank()) {
                        mensaje = "Por favor completa todos los campos"
                    } else {
                        usuario?.username = username.text
                        usuario?.email = email.text
                        mensaje = "Datos actualizados correctamente"
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2,178,191)),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Guardar cambios", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Botón volver
            Button(
                onClick = { onNavigateBack() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Volver", color = Color.White)
            }
        }
    }
}

package com.example.duocappmoviles003d

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.elevatedCardElevation
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.edit

@Composable
fun RegisterScreen(
    onRegisterSuccess: () -> Unit) {
    val context = LocalContext.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(0.92f),
            shape = RoundedCornerShape(16.dp),
            elevation = elevatedCardElevation(defaultElevation = 12.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Registrar cuenta", style = MaterialTheme.typography.headlineSmall)
                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Correo") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, singleLine = true, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(value = confirm, onValueChange = { confirm = it }, label = { Text("Confirmar contraseña") }, singleLine = true, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(12.dp))
                Button(
                    onClick = {
                        if (email.isBlank() || password.isBlank() || confirm.isBlank()) {
                            error = "Completa todos los campos"
                            return@Button
                        }
                        if (password.length < 6) {
                            error = "La contraseña debe tener al menos 6 caracteres"
                            return@Button
                        }
                        if (password != confirm) {
                            error = "Las contraseñas no coinciden"
                            return@Button
                        }

                        val prefs = getPrefs(context)
                        prefs.edit {
                            putString("email", email)
                            putString("password", password)
                        }
                        Toast.makeText(context, "Registro exitoso. Vuelve a iniciar sesión.", Toast.LENGTH_SHORT).show()
                        onRegisterSuccess()
                    },
                    modifier = Modifier.fillMaxWidth(0.6f),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text("Registrar")
                }

                if (!error.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = error ?: "", color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

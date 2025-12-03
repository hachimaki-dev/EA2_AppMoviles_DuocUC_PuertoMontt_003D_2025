package com.example.ev3.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation

@Composable
fun SignUpScreen(
    authRepository: com.example.ev3.data.auth.AuthRepository,
    onRegistered: () -> Unit,
    onBackToLogin: () -> Unit
) {
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val error = remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Crear cuenta", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(value = email.value, onValueChange = { email.value = it }, label = { Text("Correo") })
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(value = password.value, onValueChange = { password.value = it }, label = { Text("Contraseña") }, visualTransformation = PasswordVisualTransformation())
        Spacer(Modifier.height(8.dp))
        error.value?.let { Text(it, color = MaterialTheme.colorScheme.error) }
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            val res = authRepository.register(email.value.trim(), password.value)
            res.onSuccess { onRegistered() }.onFailure { error.value = it.message }
        }) { Text("Registrarme") }
        Spacer(Modifier.height(8.dp))
        Button(onClick = onBackToLogin) { Text("Volver a Login") }
    }
}


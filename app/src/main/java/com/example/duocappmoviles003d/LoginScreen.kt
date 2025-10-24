package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.coroutines.launch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.layout.ContentScale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    var usuario by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var error by remember { mutableStateOf(false) }
    var recordarSesion by remember { mutableStateOf(false) }
    var mostrarContrasena by remember { mutableStateOf(false) }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    val prefs = remember { context.getSharedPreferences("auth_prefs", android.content.Context.MODE_PRIVATE) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("Login", fontWeight = FontWeight.Bold) })
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Image(
                painter = painterResource(id = R.drawable.tomatito_login),
                contentDescription = "Fondo de Login",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.92f))) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        OutlinedTextField(
                            value = usuario,
                            onValueChange = { usuario = it; if (error) error = false },
                            label = { Text("Usuario") },
                            isError = error,
                            supportingText = { if (error) Text("Revisa tus credenciales") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        OutlinedTextField(
                            value = contrasena,
                            onValueChange = { contrasena = it; if (error) error = false },
                            label = { Text("Contraseña") },
                            visualTransformation = if (mostrarContrasena) VisualTransformation.None else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                            isError = error,
                            supportingText = { if (error) Text("Revisa tus credenciales") },
                            trailingIcon = {
                                IconButton(onClick = { mostrarContrasena = !mostrarContrasena }) {
                                    val icon = if (mostrarContrasena) Icons.Filled.VisibilityOff else Icons.Filled.Visibility
                                    val desc = if (mostrarContrasena) "Ocultar contraseña" else "Mostrar contraseña"
                                    Icon(imageVector = icon, contentDescription = desc)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(checked = recordarSesion, onCheckedChange = { recordarSesion = it })
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Recordarme")
                        }

                        Button(
                            onClick = {
                                if (usuario == "tomatito" && contrasena == "1234") {
                                    error = false
                                    prefs.edit().putBoolean("remembered", recordarSesion).apply()
                                    onLoginSuccess()
                                } else {
                                    error = true
                                    scope.launch { snackbarHostState.showSnackbar("Usuario o contraseña incorrectos") }
                                }
                            },
                            enabled = usuario.isNotBlank() && contrasena.isNotBlank(),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Ingresar")
                        }
                    }
                }

                Text(
                    text = "Usuario de ejemplo: tomatito/1234",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
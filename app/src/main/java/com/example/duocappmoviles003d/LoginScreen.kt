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
import androidx.navigation.NavController
import android.content.SharedPreferences
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    preferencias: SharedPreferences,
    snackbarHostState: SnackbarHostState
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var rememberMe by remember { mutableStateOf(preferencias.getBoolean("recordarme", false)) }

    // Scaffold para mostrar Snackbars
    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {
            // Fondo con imagen
            Image(
                painter = painterResource(id = R.drawable.tomatito_login),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.9f
            )

            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 24.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(40.dp))

                // Logo superpuesto arriba del formulario (casi todo el ancho)
                Image(
                    painter = painterResource(id = R.drawable.tomatito_logo),
                    contentDescription = "Logo Tomatito",
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .heightIn(max = 180.dp),
                    contentScale = ContentScale.Fit
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Card translúcida para el formulario
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xCCFFFFFF)),
                    shape = RoundedCornerShape(16.dp),
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Usuario (label arriba + campo centrado y ancho fijo)
                        Text(text = "Usuario", color = Color.Black)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = username,
                            onValueChange = { username = it },
                            modifier = Modifier
                                .fillMaxWidth(0.85f),
                            singleLine = true,
                            label = null,
                            placeholder = { Text("Usuario") }
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Contraseña (label arriba + campo centrado y ancho fijo)
                        Text(text = "Contraseña", color = Color.Black)
                        Spacer(modifier = Modifier.height(6.dp))
                        OutlinedTextField(
                            value = password,
                            onValueChange = { password = it },
                            modifier = Modifier
                                .fillMaxWidth(0.85f),
                            singleLine = true,
                            label = null,
                            placeholder = { Text("Contraseña") },
                            visualTransformation = PasswordVisualTransformation()
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // Recordarme alineado al inicio del contenido del Card
                        Row(
                            modifier = Modifier.fillMaxWidth(0.85f),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = rememberMe, onCheckedChange = { rememberMe = it })
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Recordarme", color = Color.Black)
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Botón centrado con ancho similar al mockup
                        Button(
                            onClick = {
                                if (username == "tomatito" && password == "1234") {
                                    preferencias.edit()
                                        .putBoolean("recordarme", rememberMe)
                                        .putBoolean("remembered", rememberMe)
                                        .apply()

                                    // Navega a Catálogo y limpia Login del backstack
                                    navController.navigate(NavigationRoutes.CATALOG) {
                                        popUpTo(NavigationRoutes.LOGIN) { inclusive = true }
                                        launchSingleTop = true
                                    }
                                } else {
                                    scope.launch { snackbarHostState.showSnackbar("Usuario o contraseña incorrectos") }
                                }
                            },
                            modifier = Modifier
                                .width(160.dp)
                                .height(40.dp)
                        ) {
                            Text(text = "Ingresar")
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Texto de ayuda como en el mockup
                        Text(
                            text = "Usuario ejemplo: tomatito/1234",
                            color = Color.Black,
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}
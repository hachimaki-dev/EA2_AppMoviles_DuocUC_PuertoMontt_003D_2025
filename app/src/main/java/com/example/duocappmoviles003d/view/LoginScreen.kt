package com.example.duocappmoviles003d.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.duocappmoviles003d.R
import com.example.duocappmoviles003d.navigation.NavigationRoute
import com.example.duocappmoviles003d.viewmodel.AuthViewModel

private val Teal = Color(0xFF02B2BF)

@Composable
fun LoginScreen(
    navController: NavHostController,
    viewModel: AuthViewModel = viewModel()
) {
    // Estados del ViewModel
    val loginState by viewModel.loginState.collectAsState()
    val error by viewModel.error.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Variables locales para los campos de texto
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    // --- CORRECCIÓN CLAVE: SEGURIDAD ---
    LaunchedEffect(loginState) {
        // Solo navegamos si es explícitamente TRUE.
        // Si es false (error) o null (esperando), no entra aquí.
        if (loginState == true) {
            navController.navigate(NavigationRoute.Catalogo.route) {
                popUpTo(NavigationRoute.Login.route) { inclusive = true }
            }
            viewModel.resetLoginState()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(color = Teal)
        } else {
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = painterResource(id = R.drawable.game),
                    contentDescription = "Logo app",
                    modifier = Modifier.height(250.dp).padding(bottom = 24.dp),
                    contentScale = ContentScale.Fit
                )

                Text(
                    text = "Iniciar Sesión",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = user,
                    onValueChange = { user = it },
                    label = { Text("Usuario") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Teal,
                        unfocusedBorderColor = Teal,
                        focusedLabelColor = Teal
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = pass,
                    onValueChange = { pass = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Teal,
                        unfocusedBorderColor = Teal,
                        focusedLabelColor = Teal
                    )
                )

                // Mostrar mensaje de error si existe
                if (!error.isNullOrEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = error!!, color = Color.Red, fontSize = 14.sp)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { viewModel.login(user, pass) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(Teal)
                ) {
                    Text("Iniciar Sesión", color = Color.Black)
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { navController.navigate(NavigationRoute.Registrar.route) },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(26.dp),
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text("Registrarse", color = Color.White)
                }

                Spacer(modifier = Modifier.height(12.dp))

                TextButton(
                    onClick = { navController.navigate(NavigationRoute.ForgotPassword.route) }
                ) {
                    Text("Olvidé mi contraseña", color = Teal)
                }
            }
        }
    }
}
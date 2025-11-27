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
import com.example.duocappmoviles003d.R
import com.example.duocappmoviles003d.viewmodel.AuthViewModel

@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit,
    onNavigateToRegister: () -> Unit,
    onNavigateToForgot: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()
    val loginState by viewModel.loginState.collectAsState()
    val error by viewModel.error.collectAsState()

    // Si login es exitoso, navegar automáticamente
    LaunchedEffect(loginState) {
        loginState?.let {
            onNavigateToHome(it.username)
        }
    }

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
                contentDescription = "Logo app",
                modifier = Modifier
                    .height(250.dp)
                    .padding(bottom = 24.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Iniciar Sesión",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // USERNAME
            OutlinedTextField(
                value = username,
                onValueChange = { viewModel.username.value = it },
                label = { Text("Usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(2, 178, 191),
                    unfocusedBorderColor = Color(2, 178, 191)
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // PASSWORD
            OutlinedTextField(
                value = password,
                onValueChange = { viewModel.password.value = it },
                label = { Text("Contraseña") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(26.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(2, 178, 191),
                    unfocusedBorderColor = Color(2, 178, 191)
                )
            )

            // ERROR MESSAGE
            if (!error.isNullOrEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error ?: "",
                    color = Color.Red,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // LOGIN BUTTON
            Button(
                onClick = { viewModel.login() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(2, 178, 191)),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Iniciar Sesión", color = Color.Black, fontWeight = FontWeight.Black)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // REGISTER BUTTON
            Button(
                onClick = { onNavigateToRegister() },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text("Registrarse", color = Color.White, fontWeight = FontWeight.Black)
            }

            Spacer(modifier = Modifier.height(12.dp))

            TextButton(
                onClick = { onNavigateToForgot() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    "Olvidé mi contraseña",
                    color = Color(2, 178, 191),
                    fontWeight = FontWeight.Black
                )
            }
        }
    }
}

package com.example.duocappmoviles003d.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.duocappmoviles003d.R
import com.example.duocappmoviles003d.login.LoginUIState
import com.example.duocappmoviles003d.login.LoginViewModel

@Composable
fun LoginScreen(
    onLoginClick: (String, String) -> Unit,
    onSignUpClick: () -> Unit,
    viewModel: LoginViewModel
) {

    val stateDeLaVistaLogin = viewModel.estoRepresentaAlModeloDeDatos.value
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var rememberMe by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
        Image(
            painter = painterResource(id = R.drawable.fondo_signup),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A0118).copy(alpha = 0.8f))
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Header
            Column(modifier = Modifier.padding(top = 40.dp)) {
                Text(
                    text = "INICIO SESIÓN",
                    fontSize = 12.sp,
                    color = Color(0xFFFF1EFF),
                    letterSpacing = 2.sp,
                    fontWeight = FontWeight.Black
                )
                Text(
                    text = "bienvenido\nde vuelta",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.Black,
                    color = Color.White,
                    lineHeight = 42.sp
                )
            }

            // Form
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                // Email field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "EMAIL",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White.copy(alpha = 0.5f),
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value =  stateDeLaVistaLogin.email,
                        onValueChange = { it -> viewModel.cuandoElEmailCambia(it) },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                "tu@email.com",
                                color = Color.White.copy(alpha = 0.3f),
                                fontSize = 16.sp
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF1EFF),
                            unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color(0xFFFF1EFF)
                        ),
                        shape = RoundedCornerShape(4.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                }

                Spacer(Modifier.height(24.dp))

                // Password field
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "CONTRASEÑA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.White.copy(alpha = 0.5f),
                        letterSpacing = 1.sp
                    )
                    Spacer(Modifier.height(8.dp))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.fillMaxWidth(),
                        placeholder = {
                            Text(
                                "••••••••",
                                color = Color.White.copy(alpha = 0.3f),
                                fontSize = 16.sp
                            )
                        },
                        visualTransformation = if (showPassword)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                        trailingIcon = {
                            Text(
                                text = if (showPassword) "OCULTAR" else "VER",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFFFF1EFF),
                                modifier = Modifier
                                    .clickable { showPassword = !showPassword }
                                    .padding(8.dp)
                            )
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFFF1EFF),
                            unfocusedBorderColor = Color.White.copy(alpha = 0.2f),
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            cursorColor = Color(0xFFFF1EFF)
                        ),
                        shape = RoundedCornerShape(4.dp),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                    )
                }

                Spacer(Modifier.height(20.dp))

                // Remember me & forgot password
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.clickable { rememberMe = !rememberMe }
                    ) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .background(
                                    if (rememberMe) Color(0xFFFF1EFF) else Color.Transparent,
                                    RoundedCornerShape(2.dp)
                                )
                                .then(
                                    if (!rememberMe) Modifier.background(
                                        Color.White.copy(alpha = 0.2f),
                                        RoundedCornerShape(2.dp)
                                    ) else Modifier
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            if (rememberMe) {
                                Text(
                                    text = "✓",
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color.Black
                                )
                            }
                        }
                        Spacer(Modifier.width(10.dp))
                        Text(
                            text = "recordarme",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.7f)
                        )
                    }

                    Text(
                        text = "¿olvidaste?",
                        fontSize = 13.sp,
                        color = Color(0xFFFF1EFF),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { /* recuperar contraseña */ }
                    )
                }

                Spacer(Modifier.height(32.dp))

                // Login button
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .background(Color(0xFFFF1EFF), RoundedCornerShape(4.dp))
                        .clickable {
                            viewModel.saludar("Pepito")
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "ENTRAR",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Black,
                        color = Color.Black,
                        letterSpacing = 2.sp
                    )
                }
            }

            // Footer
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "¿no tienes cuenta?",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.6f)
                    )
                    Spacer(Modifier.width(6.dp))
                    Text(
                        text = "CREAR UNA",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFFFF1EFF),
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier.clickable { onSignUpClick() }
                    )
                }

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "otakuwear © 2025",
                    fontSize = 11.sp,
                    color = Color.White.copy(alpha = 0.3f)
                )
            }
        }
    }
}
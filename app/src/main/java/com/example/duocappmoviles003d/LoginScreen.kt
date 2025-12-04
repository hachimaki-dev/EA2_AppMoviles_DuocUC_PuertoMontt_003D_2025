    package com.example.duocappmoviles003d

    import android.content.Context
    import android.widget.Toast
    import androidx.compose.foundation.Image
    import androidx.compose.foundation.background
    import androidx.compose.foundation.layout.*
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.*
    import androidx.compose.material3.CardDefaults.elevatedCardElevation
    import androidx.compose.runtime.*
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.blur
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Brush
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.layout.ContentScale
    import androidx.compose.ui.platform.LocalContext
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.input.PasswordVisualTransformation
    import androidx.compose.ui.text.input.VisualTransformation
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import androidx.core.content.edit

    @Composable
    fun LoginScreen(
        onLoginSuccess: () -> Unit,
        onNavigateToRegister: () -> Unit
    ) {
        val context = LocalContext.current
        // states
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var passwordVisible by remember { mutableStateOf(false) }
        var errorMessage by remember { mutableStateOf<String?>(null) }

        Box(modifier = Modifier.fillMaxSize()) {
            // Fondo pokeballs
            Image(
                painter = painterResource(id = R.drawable.pokeball_bg),
                contentDescription = "Fondo pokeball",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .blur(2.dp) // difumina un poco el fondo
            )

            // Overlay oscuro para contraste
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Color(0x88000000), Color(0x55000000))
                        )
                    )
            )

            // Card central
            Card(
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(16.dp)
                    .fillMaxWidth(0.92f),
                shape = RoundedCornerShape(16.dp),
                elevation = elevatedCardElevation(defaultElevation = 12.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "PokéDex", style = MaterialTheme.typography.headlineMedium, fontSize = 28.sp)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Ingresa a tu cuenta", style = MaterialTheme.typography.bodyMedium)

                    Spacer(modifier = Modifier.height(12.dp))

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text("Correo electrónico") },
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Contraseña") },
                        singleLine = true,
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            TextButton(onClick = { passwordVisible = !passwordVisible }) {
                                Text(if (passwordVisible) "Ocultar" else "Mostrar")
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    Button(
                        onClick = {
                            val prefs = getPrefs(context)
                            val savedEmail = prefs.getString("email", null)
                            val savedPassword = prefs.getString("password", null)

                            if (email.isBlank() || password.isBlank()) {
                                errorMessage = "Completa todos los campos"
                            } else if (savedEmail == null || savedPassword == null) {
                                errorMessage = "No hay usuarios registrados. Regístrate primero."
                            } else if (email != savedEmail || password != savedPassword) {
                                errorMessage = "Correo o contraseña incorrectos"
                            } else {
                                errorMessage = null
                                Toast.makeText(context, "Ingreso correcto", Toast.LENGTH_SHORT).show()
                                onLoginSuccess()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text("Ingresar")
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    TextButton(
                        onClick = { onNavigateToRegister() }
                    ) {
                        Text("Registrar nueva cuenta")
                    }

                    if (!errorMessage.isNullOrEmpty()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = errorMessage ?: "", color = Color.Red)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))
                    Text("O continúa con", style = MaterialTheme.typography.bodySmall)

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        SocialButton(text = "Google") {
                            Toast.makeText(context, "Simulación: conectar con Google", Toast.LENGTH_SHORT).show()
                        }
                        SocialButton(text = "Facebook") {
                            Toast.makeText(context, "Simulación: conectar con Facebook", Toast.LENGTH_SHORT).show()
                        }
                        SocialButton(text = "Correo") {
                            Toast.makeText(context, "Simulación: conectar con Correo", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun SocialButton(text: String, onClick: () -> Unit) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .height(40.dp)
                .width(120.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.White)
        ) {
            Text(text = text, color = Color.Black)
        }
    }

    fun getPrefs(context: Context) =
        context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

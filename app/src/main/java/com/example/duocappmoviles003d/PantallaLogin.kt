package com.example.duocappmoviles003d

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.theme.AzulOscuroCITT
import com.example.duocappmoviles003d.ui.theme.FondoCITT
import com.example.duocappmoviles003d.ui.theme.GrisClaroTexto
import com.example.duocappmoviles003d.ui.theme.NegroClaroTexto
import com.example.duocappmoviles003d.ui.theme.TextoPistaCITT
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT
import kotlinx.coroutines.launch

@Composable
fun PantallaLogin(navController: NavController) {

    // Variables para guardar el texto del usuario
    var rutOCorreo by remember { mutableStateOf("") }
    var clave by remember { mutableStateOf("") }

    // Contenedor para centrar el formulario
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            AzulOscuroCITT,
                            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
                        )
                        .padding(vertical = 24.dp, horizontal = 16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "CITT Connect",
                            color = Color.White,
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Plataforma de Proyectos y Tracks",
                            color = Color.White,
                            fontSize = 14.sp
                        )
                    }
                }

                // Cuerpo del Formulario
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Inicia Sesión",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Campo de Texto: RUT o Email
                    OutlinedTextField(
                        value = rutOCorreo,
                        onValueChange = { rutOCorreo = it },
                        label = { Text("RUT o Email") },
                        placeholder = { Text("Ej: 19.123.456-7 o correo@duoc.cl") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = TurquesaCITT,
                            focusedLabelColor = TurquesaCITT,
                            unfocusedPlaceholderColor = TextoPistaCITT,
                            unfocusedLabelColor = TextoPistaCITT
                        )
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Campo de Texto: Contraseña
                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        placeholder = { Text("Ingresa tu clave") },
                        modifier = Modifier.fillMaxWidth(),
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(), // Oculta la clave
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.Transparent,
                            unfocusedContainerColor = Color.Transparent,
                            focusedIndicatorColor = TurquesaCITT,
                            focusedLabelColor = TurquesaCITT,
                            unfocusedPlaceholderColor = TextoPistaCITT,
                            unfocusedLabelColor = TextoPistaCITT
                        )
                    )

                    Spacer(modifier = Modifier.height(32.dp))

                    // Botón de Acceso
                    Button(
                        onClick = {
                            navController.navigate(AppRoutes.MAIN_SCREEN) {
                                popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TurquesaCITT,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = "Acceder a la Plataforma",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Botón: Olvidaste Contraseña
                    TextButton(onClick = { /* Falta la lógica de olvidar clave -> TODO */ }) {
                        Text(
                            text = "¿Olvidaste tu Contraseña?",
                            color = TurquesaCITT,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}
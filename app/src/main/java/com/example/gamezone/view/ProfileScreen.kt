package com.example.gamezone.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.gamezone.navigation.NavigationRoute
import com.example.gamezone.viewmodel.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavHostController,
    // --- AQUÍ ESTÁ LA SOLUCIÓN ---
    // El nombre del parámetro debe ser "authViewModel"
    authViewModel: AuthViewModel
) {
    // Obtenemos el usuario actual del estado
    val usuario by authViewModel.currentUser.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Mi Perfil",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Tarjeta de Información
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Icono de Avatar
                Icon(
                    painter = rememberVectorPainter(Icons.Default.Person),
                    contentDescription = null,
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF02B2BF), CircleShape)
                        .padding(16.dp),
                    tint = Color.White
                )

                Spacer(modifier = Modifier.height(16.dp))

                if (usuario != null) {
                    // DATOS DEL USUARIO LOGUEADO
                    Text(
                        text = usuario!!.username, // Nombre de usuario
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = usuario!!.email, // Correo
                        fontSize = 16.sp,
                        color = Color.Gray
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Divider(color = Color.LightGray)

                    Spacer(modifier = Modifier.height(16.dp))

                    // Info extra (ID)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ID de Usuario: ", fontWeight = FontWeight.Bold, color = Color.Black)
                        Text("#${usuario!!.id}", color = Color.Gray)
                    }

                } else {
                    // Caso de error si se entra sin login
                    Text("No se encontró información del usuario.", color = Color.Red)
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Botón Volver al Catálogo
        OutlinedButton(
            onClick = { navController.navigate(NavigationRoute.Catalogo.route) },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Volver al Catálogo", color = Color.Gray)
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón Cerrar Sesión
        Button(
            onClick = {
                authViewModel.logout() // Limpiamos el usuario
                navController.navigate(NavigationRoute.Login.route) {
                    popUpTo(NavigationRoute.Catalogo.route) { inclusive = true }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5252)),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Cerrar Sesión", color = Color.White)
        }
    }
}
package com.example.duocappmoviles003d.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.duocappmoviles003d.navigation.NavigationRoute
import com.example.duocappmoviles003d.viewmodel.ProfileViewModel

private val Teal = Color(0xFF02B2BF)

@Composable
fun ProfileScreen(
    navController: NavHostController,
    viewModel: ProfileViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
) {
    val user = viewModel.user.collectAsState()

    Box(
        modifier = Modifier.fillMaxSize().background(Color.White),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {

            Text("Perfil del usuario", color = Color.Black, fontSize = 26.sp)

            Spacer(modifier = Modifier.height(20.dp))

            Text("Nombre: ${user.value.username}", fontSize = 18.sp, color = Color.Black)
            Text("Correo: ${user.value.email}", fontSize = 18.sp, color = Color.Black)

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    navController.navigate(NavigationRoute.Login.route) {
                        popUpTo(NavigationRoute.Home.route) { inclusive = true }
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(Color.Red)
            ) {
                Text("Cerrar sesión", color = Color.White)
            }
        }
    }
}

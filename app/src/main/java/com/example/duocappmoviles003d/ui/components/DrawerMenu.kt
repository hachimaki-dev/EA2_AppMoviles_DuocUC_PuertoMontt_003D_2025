package com.example.duocappmoviles003d.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.navigation.AppRoutes
import com.example.duocappmoviles003d.ui.theme.*

@Composable
fun MenuDrawerContent(
    navController: NavController,
    userEmail: String,
    onCloseDrawer: () -> Unit
) {
    val userName = remember(userEmail) {
        userEmail.split("@").firstOrNull()
            ?.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            ?: "Usuario"
    }

    ModalDrawerSheet(
        modifier = Modifier.widthIn(max = 300.dp),
        drawerContainerColor = FondoCITT
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(FondoCITT),
        ) {
            // Header del Drawer
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Usuario",
                    tint = AzulOscuroCITT,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Hola! $userName",
                    color = AzulOscuroCITT,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Opciones del Menú
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BotonMenu(
                    text = "Menú Principal",
                    icon = Icons.Default.Home,
                    onClick = {
                        navController.navigate(AppRoutes.createMainScreenRoute(userEmail)) {
                            popUpTo(AppRoutes.LOGIN_SCREEN) { inclusive = true }
                        }
                        onCloseDrawer()
                    }
                )
                BotonMenu(
                    text = "Proyectos y tracks",
                    icon = Icons.AutoMirrored.Filled.LibraryBooks,
                    onClick = {
                        // Navegación corregida para pasar el email
                        navController.navigate(AppRoutes.createProjectsScreenRoute(userEmail))
                        onCloseDrawer()
                    }
                )
                BotonMenu(
                    text = "Eventos Próximos",
                    icon = Icons.Default.CalendarToday,
                    onClick = onCloseDrawer
                )
                BotonMenu(
                    text = "Configuración",
                    icon = Icons.Default.Settings,
                    onClick = onCloseDrawer
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            // Botón Salir
            Button(
                onClick = {
                    navController.navigate(AppRoutes.LOGIN_SCREEN) {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RojoSalir,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Salir", fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
fun BotonMenu(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TurquesaCITT,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.weight(1f))
    }
}
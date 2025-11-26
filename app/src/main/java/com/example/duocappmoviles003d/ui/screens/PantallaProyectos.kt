package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.components.MenuDrawerContent
import com.example.duocappmoviles003d.ui.navigation.AppRoutes
import com.example.duocappmoviles003d.ui.theme.FondoCITT
import com.example.duocappmoviles003d.ui.theme.GrisClaroTexto
import com.example.duocappmoviles003d.ui.theme.NegroClaroTexto
import com.example.duocappmoviles003d.ui.theme.TextoPistaCITT
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaProyectos(navController: NavController, userEmail: String) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var busqueda by remember { mutableStateOf("") }

    val proyectos = listOf(
        "Proyecto generico 1",
        "Proyecto generico 2",
        "Proyecto generico 3"
    )

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawerContent(
                navController = navController,
                userEmail = userEmail,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Mis proyectos",
                            fontWeight = FontWeight.Bold,
                            color = NegroClaroTexto
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = NegroClaroTexto
                            )
                        }
                    },
                    actions = {
                        IconButton(onClick = {
                            navController.navigate(AppRoutes.createCreateProjectScreenRoute(userEmail))
                        }) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Añadir",
                                tint = TurquesaCITT
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = FondoCITT
                    )
                )
            },
            containerColor = FondoCITT
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
            ) {
                OutlinedTextField(
                    value = busqueda,
                    onValueChange = { busqueda = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = { Text("Buscar Proyectos") },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedLeadingIconColor = TurquesaCITT,
                        unfocusedLeadingIconColor = GrisClaroTexto
                    )
                )

                Spacer(modifier = Modifier.height(24.dp))

                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        ProyectoItem(texto = "Sistema de deteccion de fraude en sistemas b...")
                    }

                    item {
                        Text(
                            text = "Proyectos de tu track",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = NegroClaroTexto,
                            modifier = Modifier.padding(vertical = 16.dp)
                        )
                    }

                    items(proyectos.size) { index ->
                        ProyectoItem(texto = proyectos[index])
                    }
                }
            }
        }
    }
}

@Composable
fun ProyectoItem(texto: String) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = texto,
            fontSize = 16.sp,
            color = NegroClaroTexto,
            modifier = Modifier.padding(vertical = 16.dp)
        )
        Divider(color = TextoPistaCITT, thickness = 1.dp)
    }
}
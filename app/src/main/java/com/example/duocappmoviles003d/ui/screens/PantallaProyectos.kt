package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.ui.components.MenuDrawerContent
import com.example.duocappmoviles003d.ui.navigation.AppRoutes
import com.example.duocappmoviles003d.ui.theme.*
import com.example.duocappmoviles003d.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaProyectos(navController: NavController, userEmail: String, viewModel: ProjectViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val proyectosPorTrack by viewModel.todosLosProyectos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Carga todos los proyectos al iniciar
    LaunchedEffect(Unit) {
        viewModel.cargarTodosLosProyectos()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawerContent(navController, userEmail) { scope.launch { drawerState.close() } }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Explorar Proyectos", fontWeight = FontWeight.Bold) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, "Menú")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoCITT)
                )
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        navController.navigate(AppRoutes.createCreateProjectScreenRoute(userEmail))
                    },
                    containerColor = TurquesaCITT,
                    contentColor = Color.White
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Crear Proyecto")
                }
            },
            containerColor = FondoCITT
        ) { padding ->
            if (isLoading) {
                Box(Modifier.fillMaxSize(), contentAlignment = androidx.compose.ui.Alignment.Center) {
                    CircularProgressIndicator(color = TurquesaCITT)
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp)
                ) {
                    // Recorremos el map (Track -> Lista de Proyectos)
                    proyectosPorTrack.forEach { (nombreTrack, listaProyectos) ->

                        // Separador con titulo del track
                        item {
                            Text(
                                text = nombreTrack,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = TurquesaCITT,
                                modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                            )
                        }

                        // Proyectos del track
                        items(listaProyectos) { proyecto ->
                            ProyectoCardItem(proyecto)
                        }
                    }

                    // Espaciador final
                    item { Spacer(modifier = Modifier.height(50.dp)) }
                }
            }
        }
    }
}

@Composable
fun ProyectoCardItem(proyecto: Proyecto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = proyecto.nombre,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = NegroClaroTexto
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Guía: ${proyecto.profesor}",
                fontSize = 12.sp,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = proyecto.descripcion,
                fontSize = 14.sp,
                color = NegroClaroTexto,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(12.dp))
            Row {
                SuggestionChip(
                    onClick = { },
                    label = { Text("Progreso: ${proyecto.progress}%") },
                    colors = SuggestionChipDefaults.suggestionChipColors(
                        containerColor = FondoCITT
                    )
                )
            }
        }
    }
}
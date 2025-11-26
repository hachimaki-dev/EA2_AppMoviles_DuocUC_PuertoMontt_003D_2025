package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT
import com.example.duocappmoviles003d.viewmodel.ProjectViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMain(
    navController: NavController,
    userEmail: String, // <--- ¡AQUÍ ESTABA EL ERROR! Faltaba este parámetro
    viewModel: ProjectViewModel = viewModel()
) {
    val listaProyectos by viewModel.misProyectos.collectAsState()
    val proyectoActual by viewModel.proyectoSeleccionado.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Cuando la pantalla se inicia (o cambia el email), cargamos los datos de ESTE usuario
    LaunchedEffect(userEmail) {
        viewModel.cargarProyectosDelUsuario(userEmail)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(title = { Text("CITT Connect") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Text(
                "Hola, $userEmail", // <--- Ahora el saludo es dinámico
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Text("Selecciona un proyecto:", color = Color.Gray)

            Spacer(modifier = Modifier.height(16.dp))

            // MENÚ HORIZONTAL (Tracks)
            if (isLoading) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(listaProyectos) { proyecto ->
                        val trackName = proyecto.trackDetails?.nombre ?: "General"
                        val isSelected = proyecto.id == proyectoActual?.id

                        FilterChip(
                            selected = isSelected,
                            onClick = { viewModel.seleccionarProyecto(proyecto) },
                            label = { Text(trackName) },
                            leadingIcon = if (isSelected) {
                                { Icon(Icons.Default.Person, null, modifier = Modifier.size(18.dp)) }
                            } else null,
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = TurquesaCITT,
                                selectedLabelColor = Color.White
                            )
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // TARJETA DE DETALLE
            if (proyectoActual != null) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(4.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text(
                            text = proyectoActual!!.nombre,
                            fontSize = 22.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                        Divider(modifier = Modifier.padding(vertical = 12.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Profesor: ", fontWeight = FontWeight.Bold)
                            Text(proyectoActual!!.profesor)
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(proyectoActual!!.descripcion, color = Color.DarkGray)
                        Spacer(modifier = Modifier.height(16.dp))

                        Text("Progreso: ${proyectoActual!!.progress}%", fontWeight = FontWeight.Bold)

                        LinearProgressIndicator(
                            progress = proyectoActual!!.progress / 100f,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(12.dp)
                                .clip(RoundedCornerShape(50)),
                            color = TurquesaCITT
                        )
                    }
                }
            } else if (!isLoading) {
                Text("No tienes proyectos asignados en la tabla 'miembros_proyecto'.")
            }
        }
    }
}
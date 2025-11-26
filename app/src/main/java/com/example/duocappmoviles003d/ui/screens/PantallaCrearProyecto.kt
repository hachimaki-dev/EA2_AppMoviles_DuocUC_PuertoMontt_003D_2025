package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import com.example.duocappmoviles003d.ui.components.MenuDrawerContent
import com.example.duocappmoviles003d.ui.theme.*
import com.example.duocappmoviles003d.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearProyecto(navController: NavController, userEmail: String, viewModel: ProjectViewModel = viewModel()) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    // Estado de carga
    val isLoading by viewModel.isLoading.collectAsState()
    val tracks = listOf("Ciberseguridad", "Desarrollo de videojuegos", "Inteligencia Artificial", "Robotica", "Impresion 3D", "Desarrollo de Software")
    var expanded by remember { mutableStateOf(false) }
    var trackSeleccionado by remember { mutableStateOf(tracks[0]) }
    var nombreProyecto by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var integrantes by remember { mutableStateOf("") }

    var mostrarDialogo by remember { mutableStateOf(false) }
    var mensajeDialogo by remember { mutableStateOf("") }
    var dialogoEsError by remember { mutableStateOf(false) }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawerContent(
                navController = navController,
                userEmail = userEmail,
                onCloseDrawer = {
                    scope.launch { drawerState.close() }
                }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = "Solicitar creación de proyecto",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = NegroClaroTexto
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = NegroClaroTexto
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoCITT)
                )
            },
            containerColor = FondoCITT
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Seleccion de track
                Text("Selecciona un track", fontWeight = FontWeight.SemiBold)
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = it },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    OutlinedTextField(
                        value = trackSeleccionado,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Elige el track") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier.fillMaxWidth().menuAnchor(),
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )
                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        tracks.forEach { track ->
                            DropdownMenuItem(
                                text = { Text(track) },
                                onClick = {
                                    trackSeleccionado = track
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                // Nombre del proyecto
                Text("Nombre del proyecto", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = nombreProyecto,
                    onValueChange = { nombreProyecto = it },
                    label = { Text("Ingresa el nombre") },
                    placeholder = { Text("Ej: Sistema de Gestión") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                // Descripcion
                Text("Descripción", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Describe tu proyecto...") },
                    placeholder = { Text("Objetivos principales y alcance") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                // Integrantes
                Text("Integrantes", fontWeight = FontWeight.SemiBold)
                OutlinedTextField(
                    value = integrantes,
                    onValueChange = { integrantes = it },
                    label = { Text("Nombres separados por coma") },
                    placeholder = { Text("Ej: Juan Pérez, María González") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Boton enviar correo
                Button(
                    onClick = {
                        if (nombreProyecto.isBlank() || descripcion.isBlank() || integrantes.isBlank()) {
                            mensajeDialogo = "Por favor, completa todos los campos."
                            dialogoEsError = true
                            mostrarDialogo = true
                        } else {
                            // Llamada al ViewModel para enviar correo
                            viewModel.enviarSolicitudCorreo(
                                nombre = nombreProyecto,
                                track = trackSeleccionado,
                                solicitante = userEmail,
                                descripcion = descripcion,
                                integrantes = integrantes
                            ) { exito ->
                                if (exito) {
                                    mensajeDialogo = "Solicitud enviada correctamente al Coordinador!"
                                    dialogoEsError = false
                                } else {
                                    mensajeDialogo = "Error al enviar la solicitud. Intente más tarde."
                                    dialogoEsError = true
                                }
                                mostrarDialogo = true
                            }
                        }
                    },
                    enabled = !isLoading,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TurquesaCITT),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
                    } else {
                        Text("Enviar Solicitud", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // Pop-up de respuesta despues de enviar el correo
        if (mostrarDialogo) {
            AlertDialog(
                onDismissRequest = { mostrarDialogo = false },
                title = { Text(if (dialogoEsError) "Error" else "Éxito") },
                text = { Text(mensajeDialogo) },
                confirmButton = {
                    TextButton(
                        onClick = {
                            mostrarDialogo = false
                            if (!dialogoEsError) {
                                navController.popBackStack()
                            }
                        }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}
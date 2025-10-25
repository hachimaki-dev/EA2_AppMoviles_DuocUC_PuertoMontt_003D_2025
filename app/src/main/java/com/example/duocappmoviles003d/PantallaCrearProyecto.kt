package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.theme.FondoCITT
import com.example.duocappmoviles003d.ui.theme.GrisClaroTexto
import com.example.duocappmoviles003d.ui.theme.NegroClaroTexto
import com.example.duocappmoviles003d.ui.theme.TextoPistaCITT
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaCrearProyecto(navController: NavController, userEmail: String) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val tracks = listOf("Ciberseguridad", "Desarrollo de videojuegos", "Inteligencia Artificial", "Robotica","Impresion 3D","Desarrollo de Software")
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
                            text = "Solicitar creacion de proyecto",
                            fontSize = 18.sp,
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
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Selecciona un track para tu proyecto",
                    fontWeight = FontWeight.SemiBold,
                    color = NegroClaroTexto
                )

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
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            disabledContainerColor = Color.White,
                            focusedIndicatorColor = TurquesaCITT,
                            unfocusedIndicatorColor = TextoPistaCITT,
                            focusedTrailingIconColor = TurquesaCITT,
                            unfocusedTrailingIconColor = GrisClaroTexto
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
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

                Text(
                    text = "Ingresa el nombre de tu proyecto",
                    fontWeight = FontWeight.SemiBold,
                    color = NegroClaroTexto
                )
                OutlinedTextField(
                    value = nombreProyecto,
                    onValueChange = { nombreProyecto = it },
                    label = { Text("Ingresa el nombre") },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = TurquesaCITT,
                        unfocusedIndicatorColor = TextoPistaCITT
                    )
                )

                Text(
                    text = "Describe tu proyecto (Objetivos/Propósito)",
                    fontWeight = FontWeight.SemiBold,
                    color = NegroClaroTexto
                )
                OutlinedTextField(
                    value = descripcion,
                    onValueChange = { descripcion = it },
                    label = { Text("Pon tu descripcion aqui") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = TurquesaCITT,
                        unfocusedIndicatorColor = TextoPistaCITT
                    )
                )

                Text(
                    text = "Ingresa los nombres y apellidos de los integrantes",
                    fontWeight = FontWeight.SemiBold,
                    color = NegroClaroTexto
                )
                OutlinedTextField(
                    value = integrantes,
                    onValueChange = { integrantes = it },
                    label = { Text("Ej:\n- Nombre y Apellido\n- Nombre y Apellido") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        disabledContainerColor = Color.White,
                        focusedIndicatorColor = TurquesaCITT,
                        unfocusedIndicatorColor = TextoPistaCITT
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (nombreProyecto.isBlank() || descripcion.isBlank() || integrantes.isBlank()) {
                            mensajeDialogo = "Por favor, completa todos los campos."
                            dialogoEsError = true
                            mostrarDialogo = true
                        } else {
                            mensajeDialogo = "Solicitud enviada"
                            dialogoEsError = false
                            mostrarDialogo = true
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
                        text = "Enviar solicitud",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))
            }

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
}
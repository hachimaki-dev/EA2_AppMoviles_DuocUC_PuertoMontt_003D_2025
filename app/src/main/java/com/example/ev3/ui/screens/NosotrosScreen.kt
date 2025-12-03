package com.example.ev3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NosotrosScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nosotros") },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text("Sobre DigiDex", style = MaterialTheme.typography.headlineMedium)

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("🎯 Objetivo", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "DigiDex es una aplicación móvil desarrollada con Kotlin y Jetpack Compose que permite explorar el mundo de Digimon.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("🛠️ Tecnologías", style = MaterialTheme.typography.titleMedium)
                    Text("• Kotlin", style = MaterialTheme.typography.bodyLarge)
                    Text("• Jetpack Compose", style = MaterialTheme.typography.bodyLarge)
                    Text("• Arquitectura MVVM", style = MaterialTheme.typography.bodyLarge)
                    Text("• Retrofit para API REST", style = MaterialTheme.typography.bodyLarge)
                    Text("• Coil para carga de imágenes", style = MaterialTheme.typography.bodyLarge)
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("👥 Equipo", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Desarrollado por Felipe Angel e Ismael Oyarzún",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text("🌐 API", style = MaterialTheme.typography.titleMedium)
                    Text(
                        "Los datos son provistos por digi-api.com, una API pública con información de 1,488 Digimon.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

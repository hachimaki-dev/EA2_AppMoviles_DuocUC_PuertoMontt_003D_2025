package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreensita(navegarHaciaMain : (String) -> Unit){

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mi App") },
            colors = TopAppBarDefaults.colors(
                containerColor = Color.Red // <-- ¡Aquí pones tu color!
            )) },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* acción */ }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                Text("Contenido principal")
            }
        }
    )


    }



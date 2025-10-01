package com.example.duocappmoviles003d


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokemonScreen()
        }
    }
}

@Composable
fun PokemonScreen() {
    var pokemonName by remember { mutableStateOf("") }
    var displayText by remember { mutableStateOf("Escribe un nombre y presiona buscar") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Buscador de Pokémon",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = pokemonName,
            onValueChange = { newText ->
                pokemonName = newText
            },
            label = { Text("Nombre del Pokémon") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                displayText = "Funciona! Buscando: $pokemonName"
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Buscar Pokémon")
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = displayText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
package com.example.duocappmoviles003d

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Button
import androidx.compose.material3.DividerDefaults.color
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duocappmoviles003d.ui.theme.DuocAppMoviles003DTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            pantallaPokemon()
        }
    }
}

@Composable
fun pantallaPokemon(){

    var nombrePokemon by remember { mutableStateOf("") }
    var textoVisible by remember { mutableStateOf("Escribe el nombre del pokemon") }
    var textoPantalla = "Funciona! Buscando: $nombrePokemon"

    Column (
        modifier = Modifier.fillMaxSize().background(Color(0xFF272727)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            //Text: Es como un <h3>
            Text("Buscador de Pokemon")
            //Spacer: Es como un <br>
            Spacer(modifier = Modifier.height(16.dp))
            //Input
            TextField(
                value = nombrePokemon,
                onValueChange = { newText ->
                    nombrePokemon = newText
                },
                label = { Text(textoVisible) },
                modifier = Modifier.fillMaxWidth()
            )

            Button(onClick = {

            },
                Modifier.fillMaxWidth()
            ){
                Text("Buscar Pokemon")
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = textoPantalla,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
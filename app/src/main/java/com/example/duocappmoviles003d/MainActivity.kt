package com.example.duocappmoviles003d

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.duocappmoviles003d.ui.theme.DuocAppMoviles003DTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            invocarTextField()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun renderView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Hola") }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            Image(
                painter = painterResource(R.drawable.dumbell),
                contentDescription = "Barbell",
                modifier = Modifier.padding(120.dp)
            )

            Spacer(
                modifier = Modifier.height(500.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) { }
        }


    }
    Row {
        Text("Hola mundo como estan")
        Button({}) { "Soy un boton" }
    }
}
    @Composable
    fun invocarTextField(){
        var textoQueCambia by remember { mutableStateOf("") }
        TextField(
            value = textoQueCambia,
            onValueChange = {
                valor -> textoQueCambia = valor
                println(valor)
            },
            placeholder = { Text("Escribe aqui")},
            trailingIcon = {Icon(Icons.Default.AccountBox, contentDescription = "icono de usuario")}
        )
    }

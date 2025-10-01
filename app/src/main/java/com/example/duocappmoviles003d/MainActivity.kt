package com.example.duocappmoviles003d

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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

@Composable
fun invocarTextField(){

    var textoQueCambia by remember { mutableStateOf("") }

    TextField(
        value = textoQueCambia,
        onValueChange = {
            valor -> textoQueCambia = valor
        },
        placeholder = {Text("Escribe acá")},
        label = {Text("Hola")},
        leadingIcon = {Icon(Icons.Default.AccountBox, contentDescription = null )}
    )
}
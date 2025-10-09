package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }


        Box(
            modifier = Modifier.background(Color(83, 129, 179))
        ){

            Image(
                painter = painterResource(id = R.drawable.pig_wallpaper_phone),
                contentDescription = "Imagen de fondo de la APP",
                modifier = Modifier.fillMaxWidth().fillMaxSize().fillMaxHeight()
            )
            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Pantalla de Login",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color(0xFFFFFFFF),
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color.White,
                        focusedBorderColor = Color.White,
                        cursorColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (username.text.isNotBlank()) {
                            onNavigateToHome(username.text)
                        }
                    },
                    modifier = Modifier.fillMaxWidth().background(Color(0xFFFFFFFF))

                ) {
                    Text("Ir a Home")
                }
            }
        }
}
package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.unit.sp


@Composable
fun LoginScreen(
    onNavigateToHome: (String) -> Unit
) {
    var username by remember { mutableStateOf(TextFieldValue("")) }

    Box (){

        Image(painter = painterResource(
            id = R.drawable.dark_charcoal),
            contentDescription = "Fondo",
            modifier = Modifier.fillMaxWidth().fillMaxSize().fillMaxHeight()
        )

        Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido a ",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFFFFFFF),
                fontSize = 16.sp
            )
            Text(
                text = "AppGenerica",
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
                    cursorColor = Color.White,
                    disabledLabelColor = Color.White,
                    disabledBorderColor = Color.White,
                    unfocusedLabelColor = Color.White,
                    unfocusedBorderColor = Color.White,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color(0xFFC2C2C2)
                ),
                shape = RoundedCornerShape(26.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    if (username.text.isNotBlank()) {
                        onNavigateToHome(username.text)
                    }
                },
                modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6C82E8)
                )
            ) {
                Text("Ir a Home")
            }
        }
    }
}
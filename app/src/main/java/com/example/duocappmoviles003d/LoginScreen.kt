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

    Box(
        modifier =Modifier.background(Color(0xFF372932))
    ){

        Image(
            painter= painterResource(id = R.drawable.baby_yodita),
            contentDescription = "imagen de fondo de mi app",
            modifier = Modifier.fillMaxWidth().fillMaxSize().fillMaxHeight()

        )



        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Loginsito",
                style = MaterialTheme.typography.headlineMedium,
                color = Color(0xFFFFFFFF),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = "yodaaa",
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
                    focusedLabelColor = Color.Red,
                    focusedBorderColor = Color.White,
                    focusedContainerColor = Color.Black,
                    disabledLabelColor = Color.White,
                    disabledBorderColor = Color.White,
                    unfocusedBorderColor= Color.White,
                    unfocusedLabelColor= Color.Green,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Blue
                ),
                shape = RoundedCornerShape(26.dp)

            )
            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color.Red,
                    focusedBorderColor = Color.White,
                    focusedContainerColor = Color.Black,
                    disabledLabelColor = Color.White,
                    disabledBorderColor = Color.White,
                    unfocusedBorderColor= Color.White,
                    unfocusedLabelColor= Color.Green,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.Blue
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
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor= Color(0xFFcb53a0)
                )
            ) {
                Text("Ir a Home")
            }
        }
    }
}
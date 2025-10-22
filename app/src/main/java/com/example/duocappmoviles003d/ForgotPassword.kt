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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ForgotPassword(onNavigateToHome: (String) -> Unit) {
    var username by remember { mutableStateOf(TextFieldValue("")) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.game),
                contentDescription = "Logo de mi app",
                modifier = Modifier
                    .height(120.dp)
                    .padding(bottom = 24.dp)
            )

            Text(
                text = "Recuperar Contrasena",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.Black,
                fontWeight = FontWeight.Black,
                fontSize = 30.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedLabelColor = Color(2, 178, 191),
                    focusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    unfocusedLabelColor = Color(2, 178, 191),
                    unfocusedBorderColor = Color.Black,
                    focusedTextColor = Color.Black
                ),
                shape = RoundedCornerShape(26.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (username.text.isNotBlank()) {
                        onNavigateToHome(username.text)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(2, 178, 191)
                ),
                shape = RoundedCornerShape(26.dp)
            ) {
                Text(
                    text = "Enviar correo de recuperacion",
                    fontWeight = FontWeight.Black,
                    color = Color.Black
                )
            }
        }
    }
}

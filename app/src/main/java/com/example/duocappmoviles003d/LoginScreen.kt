package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
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
    var password by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier.fillMaxWidth().fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.background(Color.White)
        ){

            Image(
                alignment = Alignment.Center,
                painter = painterResource(id = R.drawable.game),
                contentDescription = "Logo de mi app"
            )


            Column(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Iniciar Sesión",
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
                        focusedLabelColor = Color(2,178,191),
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Black,
                        disabledBorderColor = Color(2,178,191),
                        unfocusedLabelColor = Color(2,178,191),
                        unfocusedBorderColor = Color.Black,
                        focusedTextColor = Color.Black

                    ),
                    shape = RoundedCornerShape(26.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedLabelColor = Color(2,178,191),
                        focusedBorderColor = Color.Black,
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.Black,
                        disabledBorderColor = Color(2,178,191),
                        unfocusedLabelColor = Color(2,178,191),
                        unfocusedBorderColor = Color.Black,
                        focusedTextColor = Color.Black

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
                        containerColor = Color(2,178,191)
                    )
                ) {
                    Text(text = "Iniciar Sesión",
                        fontWeight = FontWeight.Black,
                        color = Color.Black)
                }

                Button(
                    onClick = {
                        if (username.text.isNotBlank()) {
                            onNavigateToHome(username.text)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(2,178,191)
                    )
                ) {
                    Text(text = "Registrarse",
                        fontWeight = FontWeight.Black,
                        color = Color.Black)
                }
                Text(text = "Olvidé mi contraseña",
                    fontWeight = FontWeight.Black,
                    color = Color(2,178,191)


                )

            }
        }
    }

}
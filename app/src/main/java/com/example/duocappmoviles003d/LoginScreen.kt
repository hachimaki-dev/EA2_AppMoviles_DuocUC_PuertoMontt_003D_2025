package com.example.duocappmoviles003d

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun LoginScreen(navegarHaciaMain: () -> Unit) {
    var username by remember { mutableStateOf(TextFieldValue("")) }
    var password by remember { mutableStateOf(TextFieldValue("")) }


    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.size(25.dp))


        Image(
            painter = painterResource(id = R.drawable.hellfish_logo),
            contentDescription = "Logo de la tienda",
            modifier = Modifier.size(220.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Row(){
            Text("Login",
                fontSize = 30.sp,
                color=Color(0xFFb6121b),
                fontWeight = FontWeight.ExtraBold)


            Text(text="cito",
                fontSize=30.sp,
                color=Color(0xFF000000),
                fontWeight = FontWeight.ExtraBold
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedLabelColor = Color.Red,
                unfocusedLabelColor = Color.Black


            )
        )
        Spacer(modifier =Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Black,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedLabelColor = Color.Red,
                unfocusedLabelColor = Color.Black


            )
        )

        Spacer(modifier=Modifier.height(30.dp))
        Button(

            onClick = { navegarHaciaMain() },
            colors=ButtonDefaults.buttonColors(
                containerColor=Color(0xFFcc3300),
                contentColor=Color.White
            )
        ) {
            Text("Entrar",
                fontSize= 25.sp)

        }


    }
}
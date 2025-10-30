package com.example.duocappmoviles003d

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch


@Composable
fun ProfileScreen(navController: NavController){
    var username  by remember { mutableStateOf(TextFieldValue("")) }
    var password  by remember { mutableStateOf(TextFieldValue("")) }
    var correo  by remember { mutableStateOf(TextFieldValue("")) }
    var nombre  by remember { mutableStateOf(TextFieldValue("")) }
    val snackbarHostState = remember { SnackbarHostState() }

    // 2 :v usamos esto para que diga "cambios guardados"
    // (Lo necesitamos porque showSnackbar es una función 'suspend')
    val scope = rememberCoroutineScope()
    Scaffold(
        // 3 lol  :v
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->


        Box(){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFFFFFFF))
                    .padding(40.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ){
                Spacer(modifier = Modifier.height(40.dp))

                Row(){
                    Text("Editar",
                        fontSize = 30.sp,
                        color=Color(0xFFb6121b),
                        fontWeight = FontWeight.ExtraBold)


                    Text(text="Perfil",
                        fontSize=30.sp,
                        color=Color(0xFF000000),
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

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
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("contraseña") },
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
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = correo,
                    onValueChange = { correo = it },
                    label = { Text("cambiar email") },
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
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("cambiar nombre") },
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
                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {

                        scope.launch {
                            snackbarHostState.showSnackbar("Cambios guardados")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFcc3300),
                        contentColor = Color.White
                    )
                ) {
                    Text(
                        "Guardar Cambios",
                        fontSize = 25.sp
                    )
                }
                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {navController.navigate(NavigationRoutes.MAIN)},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFcc3300),
                        contentColor = Color.White

                    ) ){
                        Text("Volver al Inicio",
                            fontSize=25.sp)
                    }

            }

        }


    }}


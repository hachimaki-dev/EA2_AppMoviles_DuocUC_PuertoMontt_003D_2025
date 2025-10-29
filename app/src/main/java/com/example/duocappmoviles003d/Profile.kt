package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun ProfileEdit(){
    var username  by remember { mutableStateOf(TextFieldValue("")) }
    var password  by remember { mutableStateOf(TextFieldValue("")) }
    var correo  by remember { mutableStateOf(TextFieldValue("")) }
    var nombre  by remember { mutableStateOf(TextFieldValue("")) }

    Box(){
        Column(){


            Text(text="Editar Perfil")

            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Nombre de usuario") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("contraseña") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = correo,
                onValueChange = { correo = it },
                label = { Text("cambiar email") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("cambiar nombre") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )


        }

    }


}
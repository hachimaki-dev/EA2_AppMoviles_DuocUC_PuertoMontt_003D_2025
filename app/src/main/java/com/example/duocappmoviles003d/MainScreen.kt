package com.example.duocappmoviles003d

import android.R.attr.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreensita(navegarHaciaMain : (String) -> Unit) {
    var Buscador by remember { mutableStateOf("") }
    Scaffold(


        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { /* TODO: Acción para abrir el menú */ }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menú",
                            tint = Color.White // Asegura que el icono sea blanco
                        )
                    }
                },
                title = {
                    TextField(
                        value = Buscador,
                        onValueChange = {Buscador = it},
                        //singleLine = true, // esta palabra hace que solo se escriba en una linea
                        placeholder = {Text("Buscar")},
                        shape = CircleShape,
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Buscar",
                                tint = Color.Gray
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White,
                            focusedPlaceholderColor = Color.Black,
                            // para borrar raya negra que tanto odio
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent
                        ),
                        //modifier = Modifier.fillMaxWidth()
                    )
                     },

                // 2. Aquí le das los colores
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )

            )


        }
    ) { padding ->


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding) // <-- ¡Usa este padding!
                .padding(16.dp),  // Un padding extra si quieres
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("HellFish una tienda lider en productos para la industria pesquera",
                fontSize = (20.sp))
            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.pesca_industriales),
                contentDescription = "Logo de la tienda",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Tanto la pesca industrial como artesanal" +
                    "son nuestros principales clientes")
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painter = painterResource(id = R.drawable.salmonera_imagen),
                contentDescription = "Logo de la tienda",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text("Ademas de presencia en la industria salmonera" +
                    "da las mas grandes en chile")






        }
    }
}


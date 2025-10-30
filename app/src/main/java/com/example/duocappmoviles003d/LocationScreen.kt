package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationScreen(navController: NavController){
    var Buscador by remember { mutableStateOf("") }
    var menuExpanded by remember { mutableStateOf(false) }
    //para el boton de configuracion hacemos e mismo prosedimiento
    var configuracionExpanded by remember {mutableStateOf(false)}
    Scaffold(


        topBar = {
            TopAppBar(
                navigationIcon = {

                    Box{ //envolvemos en un box para que funcione como ancla para elmenu
                        IconButton(onClick = { menuExpanded=true }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú",
                                tint = Color.White // Asegura que el icono sea blanco
                            )
                        }
                        //escencial para darle al menusito su funcionsita
                        DropdownMenu(
                            expanded = menuExpanded,
                            onDismissRequest = {menuExpanded=false}// esto es para cerrar cuando se toque afuera :v
                        ) {
                            DropdownMenuItem(
                                text = { Text("Ir al Inicio") },
                                onClick = {
                                    navController.navigate(NavigationRoutes.MAIN)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {Text("Productos")},
                                onClick = {
                                    navController.navigate(NavigationRoutes.PRODUCT)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = {Text("Nuestras Tiendas")},
                                onClick = {
                                    navController.navigate(NavigationRoutes.LOCATION)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Ir a Mi Perfil") },
                                onClick = {
                                    navController.navigate(NavigationRoutes.PROFILE)
                                    menuExpanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Ir al carrito") },
                                onClick = {
                                    navController.navigate(NavigationRoutes.CARRITO)
                                    menuExpanded = false
                                }
                            )
                        }
                    }},
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
                actions = {
                    Box{
                        IconButton(onClick = { configuracionExpanded=true }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Configuración",
                                tint = Color.White // Asegura que sea blanco
                            )

                        }
                        DropdownMenu(
                            expanded = configuracionExpanded,
                            onDismissRequest = {configuracionExpanded=false}

                        ) {
                            DropdownMenuItem(
                                text = {Text("cerrar sesion")},
                                onClick = {
                                    navController.navigate(NavigationRoutes.LOGIN)
                                    configuracionExpanded = false
                                }
                            )
                        }

                    }},

                // 2. Aquí le das los colores
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Red,
                    titleContentColor = Color.White
                )

            )


        }
    ) { padding ->


       Column(
           modifier=Modifier.fillMaxSize().padding(padding),
           horizontalAlignment = Alignment.CenterHorizontally
       )
        {
            Image(
                painter = painterResource(id = R.drawable.ubicacion),
                contentDescription = "icono de location",
                modifier = Modifier.size(80.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.mapa_pto),
                contentDescription = "mapa puerto montt",
                modifier = Modifier.size(150.dp)
            )
            Text("Av Angelmo 7878",
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(30.dp))
            Image(
                painter = painterResource(id = R.drawable.mapa_calbuco),
                contentDescription = "mapa calbuco",
                modifier = Modifier.size(150.dp)
            )
            Text("Av Brasil 123",
                fontWeight = FontWeight.Bold)

            Spacer(modifier = Modifier.height(30.dp))

            Image(
                painter = painterResource(id = R.drawable.mapa_chiloe),
                contentDescription = "mapa chiloe",
                modifier = Modifier.size(150.dp)
            )
            Text("Calle Los chilotitos 2030",
                fontWeight = FontWeight.Bold)




        }
    }
}






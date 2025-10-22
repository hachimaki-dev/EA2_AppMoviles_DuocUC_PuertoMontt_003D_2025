package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.automirrored.filled.LibraryBooks
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.theme.AmarilloTarjeta
import com.example.duocappmoviles003d.ui.theme.AzulOscuroCITT
import com.example.duocappmoviles003d.ui.theme.FondoCITT
import com.example.duocappmoviles003d.ui.theme.GrisClaroTexto
import com.example.duocappmoviles003d.ui.theme.NegroClaroTexto
import com.example.duocappmoviles003d.ui.theme.TurquesaCITT
import com.example.duocappmoviles003d.ui.theme.VerdeProgreso
import kotlinx.coroutines.launch
import com.example.duocappmoviles003d.ui.theme.RojoSalir


// Color para el botón Salir, puedes moverlo a Color.kt si quieres

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMenuPrincipal(navController: NavController) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawerContent(
                navController = navController,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        // Scaffold sirve para dar una "estructura" como la de una barra superior)
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = FondoCITT,
                        navigationIconContentColor = NegroClaroTexto
                    )
                )
            },
            containerColor = FondoCITT
        ) { padding ->

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(horizontal = 24.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = "Menu Principal",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = NegroClaroTexto
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Sección Proyecto Asignado
                SeccionTitulo(
                    icono = Icons.Default.Star,
                    titulo = "Proyecto Asignado"
                )
                Text(
                    text = "Sist. de deteccion de fraude",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = NegroClaroTexto,
                    modifier = Modifier.padding(start = 4.dp)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Row con Track, Progreso y Profesor
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top
                ) {
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        titulo = "Track Asignado",
                        icono = Icons.Default.Lock,
                        texto = "Track de Ciberseguridad"
                    )
                    InfoProgreso(
                        modifier = Modifier.weight(1f),
                        titulo = "Progreso del proyecto",
                        progreso = 0.64f
                    )
                    InfoItem(
                        modifier = Modifier.weight(1f),
                        titulo = "Profesor Guia",
                        icono = Icons.Default.School,
                        texto = "Juan Carlos Bodoque"
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Sección Proximos Eventos
                SeccionTitulo(
                    icono = Icons.Default.CalendarToday,
                    titulo = "Mis proximos eventos"
                )
                Text(
                    text = "No tienes eventos agendados",
                    fontSize = 16.sp,
                    color = GrisClaroTexto,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 24.dp)
                )

                // Seccion integrantes
                SeccionTitulo(
                    icono = Icons.Default.List,
                    titulo = "Integrantes de tu proyecto"
                )

                // Row con estudiantes y lista
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Cantidad de estudiantes del track
                    Card(
                        modifier = Modifier.weight(0.4f),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = AmarilloTarjeta)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "10",
                                fontSize = 36.sp,
                                fontWeight = FontWeight.Bold,
                                color = NegroClaroTexto
                            )
                            Text(
                                text = "Estudiantes",
                                fontSize = 14.sp,
                                color = NegroClaroTexto
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(16.dp))

                    // Columna de Integrantes
                    Column(
                        modifier = Modifier.weight(0.6f), // Ocupa el 60%
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        IntegranteItem(nombre = "Ada Lovelace")
                        IntegranteItem(nombre = "Mark Hopper")
                        IntegranteItem(nombre = "Margaret Hamilto") // Acortado
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}


@Composable
fun MenuDrawerContent(
    navController: NavController,
    onCloseDrawer: () -> Unit
) {
    ModalDrawerSheet(
        modifier = Modifier.widthIn(max = 300.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(AzulOscuroCITT),
        ) {
            // Encabezado del Menú
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Usuario",
                    tint = Color.White,
                    modifier = Modifier.size(100.dp)
                )
                Text(
                    text = "Hola! Usuario",
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            // Botones del Menú
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                BotonMenu(
                    text = "Menú Principal",
                    icon = Icons.Default.Home,
                    onClick = {
                        navController.navigate(AppRoutes.MAIN_SCREEN) {
                            popUpTo(AppRoutes.MAIN_SCREEN) { inclusive = true }
                        }
                        onCloseDrawer()
                    }
                )
                BotonMenu(
                    text = "Proyectos y tracks",
                    icon = Icons.AutoMirrored.Filled.LibraryBooks,
                    onClick = {
                        navController.navigate(AppRoutes.PROJECTS_SCREEN)
                        onCloseDrawer()
                    }
                )
                BotonMenu(
                    text = "Eventos Proximos",
                    icon = Icons.Default.CalendarToday,
                    onClick = onCloseDrawer
                )
                BotonMenu(
                    text = "Configuracion",
                    icon = Icons.Default.Settings,
                    onClick = onCloseDrawer
                )
            }

            Spacer(modifier = Modifier.weight(1f)) // Empuja el botón Salir al fondo

            // Botón Salir
            Button(
                onClick = {
                    navController.navigate(AppRoutes.LOGIN_SCREEN) {
                        popUpTo(0)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = RojoSalir,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Salir", fontWeight = FontWeight.Bold)
            }
        }
    }
}

// Botón personalizado para el menú
@Composable
fun BotonMenu(text: String, icon: ImageVector, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = TurquesaCITT,
            contentColor = AzulOscuroCITT
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text, fontWeight = FontWeight.Bold, textAlign = TextAlign.Start)
        Spacer(modifier = Modifier.weight(1f)) // Empuja el texto a la izquierda
    }
}


// Componentes Reutilizables

// Título de sección
@Composable
fun SeccionTitulo(icono: ImageVector, titulo: String) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = NegroClaroTexto,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = titulo,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroClaroTexto
            )
        }
        Divider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 12.dp),
            color = GrisClaroTexto,
            thickness = 1.dp
        )
    }
}

// Item de info (Track y Profesor)
@Composable
fun InfoItem(
    titulo: String,
    icono: ImageVector,
    texto: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = titulo,
            fontSize = 12.sp,
            color = GrisClaroTexto,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Icon(
            imageVector = icono,
            contentDescription = null,
            modifier = Modifier.size(32.dp),
            tint = NegroClaroTexto
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = texto,
            fontSize = 14.sp,
            color = NegroClaroTexto,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
    }
}

// Círculo de progreso
@Composable
fun InfoProgreso(
    titulo: String,
    progreso: Float,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Text(
            text = titulo,
            fontSize = 12.sp,
            color = GrisClaroTexto,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Box(contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                progress = { progreso },
                modifier = Modifier.size(70.dp),
                color = VerdeProgreso,
                trackColor = FondoCITT,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round
            )
            // Texto "64%"
            Text(
                text = "${(progreso * 100).toInt()}%",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroClaroTexto
            )
        }
    }
}

// Item de integrante
@Composable
fun IntegranteItem(nombre: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        // Imagen de perfil
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(GrisClaroTexto)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = nombre,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = NegroClaroTexto
        )
    }
}
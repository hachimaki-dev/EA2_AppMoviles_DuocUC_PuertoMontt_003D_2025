package com.example.duocappmoviles003d.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.duocappmoviles003d.ui.components.MenuDrawerContent
import com.example.duocappmoviles003d.ui.theme.*
import com.example.duocappmoviles003d.viewmodel.ProjectViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaMain(
    navController: NavController,
    userEmail: String,
    viewModel: ProjectViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Datos del ViewModel
    val listaProyectos by viewModel.misProyectos.collectAsState()
    val proyectoActual by viewModel.proyectoSeleccionado.collectAsState()
    val integrantesReales by viewModel.integrantes.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    // Cargar proyectos al entrar
    LaunchedEffect(userEmail) {
        viewModel.cargarProyectosDelUsuario(userEmail)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            MenuDrawerContent(
                navController = navController,
                userEmail = userEmail,
                onCloseDrawer = { scope.launch { drawerState.close() } }
            )
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, "Menú", tint = NegroClaroTexto)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = FondoCITT)
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

                // Botones selectores de proyecto (1, 2, 3)
                if (isLoading) {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = TurquesaCITT)
                } else if (listaProyectos.isNotEmpty()) {
                    Text("Selecciona Proyecto:", fontSize = 12.sp, color = GrisClaroTexto)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        listaProyectos.forEachIndexed { index, proyecto ->
                            val isSelected = proyecto.id == proyectoActual?.id
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(if (isSelected) TurquesaCITT else Color.LightGray)
                                    .clickable { viewModel.seleccionarProyecto(proyecto) }
                            ) {
                                Text(
                                    text = "${index + 1}",
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                } else {
                    Text("No se encontraron proyectos", color = Color.Red)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Si hay un proyecto seleccionado muestra info si no muestra un mensaje de error
                 if (proyectoActual != null) {

                    SeccionTitulo(
                        icono = Icons.Default.Star,
                        titulo = "Proyecto Asignado"
                    )

                    // Nombre del Proyecto
                    Text(
                        text = proyectoActual!!.nombre,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = NegroClaroTexto,
                        modifier = Modifier.padding(start = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    // Row con info (TRACK, PROGRESO, PROFESOR)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.Top
                    ) {
                        InfoItem(
                            modifier = Modifier.weight(1f),
                            titulo = "Track Asignado",
                            icono = Icons.Default.Lock,
                            texto = proyectoActual!!.trackDetails?.nombre ?: "Sin Track"
                        )

                        InfoProgreso(
                            modifier = Modifier.weight(1f),
                            titulo = "Progreso",
                            progreso = proyectoActual!!.progress / 100f
                        )

                        InfoItem(
                            modifier = Modifier.weight(1f),
                            titulo = "Profesor Guia",
                            icono = Icons.Default.School,
                            texto = proyectoActual!!.profesor
                        )
                    }

                    Spacer(modifier = Modifier.height(32.dp))

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

                    SeccionTitulo(
                        icono = Icons.Default.List,
                        titulo = "Integrantes de tu proyecto"
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Tarjeta contador
                        Card(
                            modifier = Modifier.weight(0.4f),
                            shape = RoundedCornerShape(20.dp),
                            colors = CardDefaults.cardColors(containerColor = AmarilloTarjeta)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${integrantesReales.size}",
                                    fontSize = 36.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = NegroClaroTexto
                                )
                                Text("Estudiantes", fontSize = 14.sp, color = NegroClaroTexto)
                            }
                        }

                        Spacer(modifier = Modifier.width(16.dp))

                        // Lista de nombres
                        Column(
                            modifier = Modifier.weight(0.6f),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            // Itera sobre la lista descargada de Supabase
                            integrantesReales.forEach { usuario ->
                                IntegranteItem(nombre = usuario.nombre)
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                }
            }
        }
    }
}

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
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
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
            textAlign = TextAlign.Center,
            lineHeight = 16.sp
        )
    }
}

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
            // Animación del círculo basada en el progreso de la BD
            CircularProgressIndicator(
                progress = { progreso },
                modifier = Modifier.size(70.dp),
                color = VerdeProgreso,
                trackColor = FondoCITT,
                strokeWidth = 8.dp,
                strokeCap = StrokeCap.Round,
            )
            Text(
                text = "${(progreso * 100).toInt()}%",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = NegroClaroTexto
            )
        }
    }
}

@Composable
fun IntegranteItem(nombre: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "Perfil",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .background(GrisClaroTexto)
                .padding(4.dp)
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
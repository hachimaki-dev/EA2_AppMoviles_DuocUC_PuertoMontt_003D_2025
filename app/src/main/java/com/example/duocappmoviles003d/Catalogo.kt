package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CatalogoScreen(navegarHaciaContacto: () -> Unit, navegarHaciaConfiguracion: () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    var searchText by remember { mutableStateOf("") }
    var filterText by remember { mutableStateOf("") }
    var cartCount by remember { mutableStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    val productosBase = remember {
        listOf(
            Producto("Tomaco", "Edición especial de la granja", R.drawable.tomaco),
            Producto("Tomate Cherry", "Pequeños y dulces", R.drawable.ic_launcher_foreground),
            Producto("Tomate de Oro", "Color dorado y sabor intenso", R.drawable.tomate_de_oro),
            Producto("Tomate Aji", "Picante y sabroso", R.drawable.tomate_aji)
        )
    }
    val productosFiltrados = remember(filterText) {
        if (filterText.isBlank()) productosBase
        else productosBase.filter {
            it.titulo.contains(filterText, ignoreCase = true) ||
                    it.detalle.contains(filterText, ignoreCase = true)
        }
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "Menú",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
                NavigationDrawerItem(
                    label = { Text("Catálogo") },
                    selected = true,
                    onClick = { scope.launch { drawerState.close() } }
                )
                NavigationDrawerItem(
                    label = { Text("Configuración") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close(); navegarHaciaConfiguracion() } }
                )
                NavigationDrawerItem(
                    label = { Text("Contacto") },
                    selected = false,
                    onClick = { scope.launch { drawerState.close(); navegarHaciaContacto() } }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Abrir menú")
                        }
                    },
                    title = {
                        TextField(
                            value = searchText,
                            onValueChange = { searchText = it },
                            singleLine = true,
                            placeholder = { Text("Buscar producto") },
                            modifier = Modifier.fillMaxWidth(),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            )
                        )
                    },
                    actions = {
                        Text(text = "Carrito: $cartCount", modifier = Modifier.padding(end = 8.dp))
                        IconButton(onClick = { filterText = searchText }) {
                            Icon(Icons.Filled.Search, contentDescription = "Buscar")
                        }
                        IconButton(onClick = { navegarHaciaConfiguracion() }) {
                            Icon(Icons.Filled.Settings, contentDescription = "Configuración")
                        }
                    }
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
        ) { padding ->
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(padding)) {
                Surface(color = Color(0xFFD32F2F), modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Bienvenido a Tomatito",
                        color = Color.White,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(productosFiltrados) { producto ->
                        ProductoItem(
                            producto = producto,
                            onAgregarCarrito = {
                                cartCount++
                                scope.launch {
                                    snackbarHostState.showSnackbar("Agregado: ${producto.titulo}")
                                }
                            },
                            onVerMas = {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Detalles próximamente")
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductoItem(
    producto: Producto,
    onAgregarCarrito: () -> Unit,
    onVerMas: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.padding(12.dp)) {
            Image(
                painter = painterResource(id = producto.drawableRes),
                contentDescription = producto.titulo,
                modifier = Modifier.size(80.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(producto.titulo, style = MaterialTheme.typography.titleMedium)
                Text(producto.detalle, style = MaterialTheme.typography.bodyMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    FilledTonalButton(onClick = onAgregarCarrito) {
                        Text("Agregar al carrito")
                    }
                    TextButton(onClick = onVerMas) { Text("Ver más") }
                }
            }
        }
    }
}

private data class Producto(
    val titulo: String,
    val detalle: String,
    val drawableRes: Int
)
package com.example.duocappmoviles003d.Cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCheckout: () -> Unit, // 1. Agregamos el parámetro de navegación
    viewModel: CartViewModel = viewModel()
) {
    val items by viewModel.items.collectAsState()

    // Cargar carrito al entrar a la pantalla
    LaunchedEffect(Unit) {
        viewModel.loadCart()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Mi Carrito") },
                // 2. Implementamos el botón de volver
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize() // Aseguramos que ocupe todo el espacio
        ) {
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items) { item ->
                    Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
                        Row(modifier = Modifier.padding(16.dp)) {
                            Text(
                                "${item.product.name} (x${item.quantity})",
                                modifier = Modifier.weight(1f)
                            )
                            Text("$${item.product.price * item.quantity}")
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Total: $${viewModel.getTotal()}",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = onNavigateToCheckout, // 3. Conectamos la acción al botón
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp),
                enabled = items.isNotEmpty() // Opcional: Deshabilitar si está vacío
            ) {
                Text("Pagar")
            }
        }
    }
}
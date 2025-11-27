package com.example.duocappmoviles003d.ProductCatalogue

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.duocappmoviles003d.Cart.CartViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCatalogueScreen(
    onNavigateToCart: () -> Unit,
    catViewModel: CatalogueViewModel = viewModel(),
    cartViewModel: CartViewModel = viewModel()
) {
    val products by catViewModel.products.collectAsState()
    val isLoading by catViewModel.isLoading.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo") },
                actions = {
                    IconButton(onClick = onNavigateToCart) {
                        Icon(Icons.Default.ShoppingCart, contentDescription = "Carrito")
                    }
                }
            )
        }
    ) { padding ->
        if (isLoading) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyColumn(Modifier.padding(padding).padding(8.dp)) {
                items(products) { product ->
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                    ) {
                        Row(Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            Column(Modifier.weight(1f)) {
                                Text(product.name, style = MaterialTheme.typography.titleMedium)
                                Text("$${product.price}", style = MaterialTheme.typography.bodyLarge)
                            }
                            Button(onClick = { cartViewModel.addToCart(product) }) {
                                Text("Añadir")
                            }
                        }
                    }
                }
            }
        }
    }
}
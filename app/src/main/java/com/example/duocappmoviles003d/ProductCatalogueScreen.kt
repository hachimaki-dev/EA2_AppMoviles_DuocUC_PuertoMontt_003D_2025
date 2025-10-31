package com.example.duocappmoviles003d

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.duocappmoviles003d.Product


val sampleProducts = listOf(
    Product(101, "Disco de coldplay", 45990.0, 1),
    Product(102, "Disco de star wars", 29990.0, 5),
    Product(103, "Disco de mickel jackson (1L)", 8500.0, 6),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCatalogueScreen(
    onNavigateBack: () -> Unit,
    onNavigateToCart: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo Deluxe ") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryRed,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = onNavigateToCart) {
                        Icon(Icons.Filled.ShoppingCart, contentDescription = "Carrito", tint = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp),
            contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)
        ) {
            items(sampleProducts, key = { it.id }) { product ->
                ProductCard(product = product)
            }
        }
    }
}


@Composable
fun ProductCard(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Sección de Texto
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = DarkBackground
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    "$${String.format("%,.0f", product.price)} CLP", // Formato de precio chileno
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = PrimaryRed
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    "Stock: ${product.stock} unidades",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Gray
                )
            }

            // Botón de Compra
            Button(
                onClick = { CartManager.addToCart(product) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                enabled = product.stock > 0,
                modifier = Modifier.height(40.dp)
            ) {
                Text("Añadir")
            }
        }
    }
}
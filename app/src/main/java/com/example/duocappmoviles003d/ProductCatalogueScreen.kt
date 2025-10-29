package com.example.duocappmoviles003d

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Product(
    val id: Int,
    val name: String,
    val price: Double,
    val stock: Int,
    val imageResId: Int
)


val sampleProducts = listOf(
    Product(101, "Disco de coldplay", 45990.0, 15, R.drawable.product_1_shoe),
    Product(102, "Disco de star wars", 29990.0, 8, R.drawable.product_2_bag),
    Product(103, "Disco de mickel jackson (1L)", 8500.0, 30, R.drawable.product_3_bottle),

)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductCatalogueScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Catálogo de Productos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = PrimaryRed,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver", tint = Color.White)
                    }
                }
            )
        }
    ) { paddingValues ->
        // Usa LazyColumn para listar eficientemente muchos productos
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
        ) {
            item {
                Text(
                    text = "Total de Productos en Venta",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(16.dp)
                )
            }

            items(sampleProducts) { product ->
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
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagen del Producto
            Image(
                painter = painterResource(id = product.imageResId),
                contentDescription = product.name,
                modifier = Modifier
                    .size(80.dp)
                    .padding(end = 16.dp),
                contentScale = ContentScale.Crop // Para que la imagen se vea bien en el espacio
            )


            Column(modifier = Modifier.weight(1f)) {
                Text(product.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Text("ID: ${product.id}", style = MaterialTheme.typography.bodySmall)
                Text("Precio: $${String.format("%.2f", product.price)}", style = MaterialTheme.typography.bodyLarge, color = PrimaryRed)
                Text("Stock: ${product.stock} unidades", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }

            // Botón de Acción
            Button(
                onClick = {
                    CartManager.addToCart(product) },
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed)
            ) {
                Text("Comprar")
            }
        }
    }
}
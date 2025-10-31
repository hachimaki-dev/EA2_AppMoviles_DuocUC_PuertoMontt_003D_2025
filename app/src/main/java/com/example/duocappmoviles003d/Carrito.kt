package com.example.duocappmoviles003d

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class CartItem(
    val product: Product,
    var quantity: Int = 1
)
object CartManager {
    val cartItems = mutableStateListOf<CartItem>()

    fun addToCart(product: Product) {
        val existingItem = cartItems.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity++
        } else {
            cartItems.add(CartItem(product))
        }
    }

    fun removeFromCart(productId: Int) {
        cartItems.removeAll { it.product.id == productId }
    }

    fun updateQuantity(productId: Int, newQuantity: Int) {
        val item = cartItems.find { it.product.id == productId }
        item?.quantity = newQuantity
        if (newQuantity <= 0) {
            removeFromCart(productId)
        }
    }

    fun getTotalPrice(): Double {
        return cartItems.sumOf { it.product.price * it.quantity }
    }

    fun clearCart() {
        cartItems.clear()
    }
}

// Carrito.kt

// ... (imports y otras clases) ...

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VistaCarrito(
    onNavigateBack: () -> Unit,
    // <--- ¡ESTE PARÁMETRO DEBE ESTAR AQUÍ!
    onNavigateToCheckout: () -> Unit
) {
    {
    // ...
    // Dentro del botón "Finalizar Compra" debe llamarse a onNavigateToCheckout()
    // ...
}
    val cartItems = remember { CartManager.cartItems }

    Scaffold(
        // ... (TopAppBar se mantiene igual)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (cartItems.isEmpty()) {
                // ... (Mensaje de carrito vacío)
            } else {
                // Lista de productos en el carrito
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 8.dp)
                ) {
                    items(cartItems, key = { it.product.id }) { cartItem ->
                        CartItemCard(cartItem = cartItem)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }

                // Total y botón de comprar
                Card(
                    // ... (Configuración de Card)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        // ... (Fila de Total)

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            // ¡ACCIÓN MODIFICADA!
                            onClick = onNavigateToCheckout,
                            modifier = Modifier.fillMaxWidth().height(50.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed)
                        ) {
                            Text(
                                "Finalizar Compra",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}
@Composable
fun CartItemCard(cartItem: CartItem) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    cartItem.product.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text("Precio: $${String.format("%.2f", cartItem.product.price)}")
                Text("Subtotal: $${String.format("%.2f", cartItem.product.price * cartItem.quantity)}")
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = {
                        CartManager.updateQuantity(cartItem.product.id, cartItem.quantity - 1)
                    }
                ) {
                    Text("-", style = MaterialTheme.typography.titleLarge)
                }

                Text(
                    cartItem.quantity.toString(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                IconButton(
                    onClick = {
                        CartManager.updateQuantity(cartItem.product.id, cartItem.quantity + 1)
                    }
                ) {
                    Text("+", style = MaterialTheme.typography.titleLarge)
                }
            }

            IconButton(
                onClick = { CartManager.removeFromCart(cartItem.product.id) }
            ) {
                Text("X", style = MaterialTheme.typography.bodyMedium, color = PrimaryRed)
            }
        }
    }
}
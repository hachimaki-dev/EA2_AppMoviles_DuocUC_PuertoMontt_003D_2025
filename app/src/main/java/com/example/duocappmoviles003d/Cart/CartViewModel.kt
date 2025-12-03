package com.example.duocappmoviles003d.Cart
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.ProductCatalogue.Product
import com.example.duocappmoviles003d.SupabaseRepository
import com.example.duocappmoviles003d.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import android.util.Log
class CartViewModel : ViewModel() {
    private val repository = SupabaseRepository()
    private val _items = MutableStateFlow<List<CartItemUi>>(emptyList())
    val items: StateFlow<List<CartItemUi>> = _items
    private val _loading = MutableStateFlow(false)

    fun loadCart() {
        val userId = UserSession.currentUser?.id ?: return
        viewModelScope.launch {
            _loading.value = true
            val cart = repository.getOrCreateActiveCart(userId)
            if (cart?.id != null) {
                val list = repository.getCartItems(cart.id)
                _items.value = list.map { CartItemUi(it.first, it.second) }
            }
            _loading.value = false
        }
    }

    fun addToCart(product: Product) {
        Log.d("CartViewModel", "Botón presionado para producto: ${product.name}")

        val user = UserSession.currentUser
        if (user == null) {
            Log.e("CartViewModel", "ERROR CRÍTICO: El usuario es NULL. No se puede agregar al carrito.")
            return
        }

        val userId = user.id
        if (userId == null) {
            Log.e("CartViewModel", "ERROR: El usuario tiene ID nulo.")
            return
        }

        Log.d("CartViewModel", "Usuario detectado (ID: $userId). Iniciando proceso...")

        viewModelScope.launch {
            val cart = repository.getOrCreateActiveCart(userId)
            if (cart?.id != null) {
                repository.addProductToCart(cart.id, product)
                loadCart()
            } else {
                Log.e("CartViewModel", "Error: No se pudo obtener/crear el carrito.")
            }
        }
    }

    fun getTotal(): Double = _items.value.sumOf { it.product.price * it.quantity }
}
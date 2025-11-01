package com.example.duocappmoviles003d

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class CartItem(
    val productId: String,
    val title: String,
    val price: Double,
    val imageRes: Int,
    val quantity: Int
)

class CartViewModel : ViewModel() {
    private val _items = MutableStateFlow<Map<String, CartItem>>(emptyMap())
    val items: StateFlow<Map<String, CartItem>> = _items.asStateFlow()

    fun addItem(
        productId: String,
        title: String,
        price: Double,
        imageRes: Int,
        qty: Int = 1
    ) {
        val current = _items.value.toMutableMap()
        val existing = current[productId]
        val newQty = (existing?.quantity ?: 0) + qty
        if (newQty <= 0) {
            current.remove(productId)
        } else {
            current[productId] = CartItem(productId, title, price, imageRes, newQty)
        }
        _items.value = current
    }

    fun updateQty(productId: String, qty: Int) {
        val current = _items.value.toMutableMap()
        val existing = current[productId]
        if (existing != null) {
            if (qty <= 0) current.remove(productId)
            else current[productId] = existing.copy(quantity = qty)
            _items.value = current
        }
    }

    fun removeItem(productId: String) {
        val current = _items.value.toMutableMap()
        current.remove(productId)
        _items.value = current
    }

    fun clear() {
        _items.value = emptyMap()
    }

    fun itemCount(): Int = _items.value.values.sumOf { it.quantity }
    fun total(): Double = _items.value.values.sumOf { it.price * it.quantity }
}
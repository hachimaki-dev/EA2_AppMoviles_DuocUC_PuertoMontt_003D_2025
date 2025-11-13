package com.example.duocappmoviles003d.viewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class Producto(val id: Int, val nombre: String)

class CatalogoViewModel : ViewModel() {

    private val _productos = MutableStateFlow(
        listOf(
            Producto(1, "Coca-Cola 1L"),
            Producto(2, "Sprite 1.5L"),
            Producto(3, "Fanta 1.5L"),
            Producto(4, "Agua Dasani 600ml")
        )
    )
    val productos: StateFlow<List<Producto>> = _productos

    private val _carrito = MutableStateFlow<List<Producto>>(emptyList())
    val carrito: StateFlow<List<Producto>> = _carrito

    fun agregarAlCarrito(producto: Producto) {
        _carrito.value = _carrito.value + producto
    }
}

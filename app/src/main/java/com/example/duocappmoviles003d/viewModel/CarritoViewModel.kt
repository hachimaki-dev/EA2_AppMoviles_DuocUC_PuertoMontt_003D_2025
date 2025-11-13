package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarritoViewModel(
    private val repo: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _carritoAgregado = MutableStateFlow(false)
    val carritoAgregado: StateFlow<Boolean> = _carritoAgregado

    fun agregarProducto(usuarioId: Long, productoId: Long, cantidad: Int = 1) {
        viewModelScope.launch {
            val ok = repo.agregarAlCarrito(usuarioId, productoId, cantidad)
            _carritoAgregado.value = ok
        }
    }
}

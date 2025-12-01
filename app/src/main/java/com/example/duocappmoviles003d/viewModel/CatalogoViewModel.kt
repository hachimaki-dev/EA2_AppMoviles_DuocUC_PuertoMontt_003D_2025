package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.Repository.ProductoRepository
import com.example.duocappmoviles003d.model.Producto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CatalogoViewModel : ViewModel() {

    private val repo = ProductoRepository()

    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje

    fun cargarProductos() {
        viewModelScope.launch {
            val lista = repo.obtenerProductos()

            if (lista != null) {
                _productos.value = lista
                _mensaje.value = ""
            } else {
                _mensaje.value = "No se pudieron cargar los productos"
            }
        }
    }
}

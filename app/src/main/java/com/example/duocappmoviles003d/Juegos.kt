package com.example.duocappmoviles003d

import androidx.annotation.DrawableRes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

data class Juego(
    val nombre: String,
    val precio: Int, // en CLP
    @DrawableRes val imagen: Int,
    val descripcion: String,
    var cantidad: Int = 1
) {
    var cantidadState by mutableStateOf(cantidad)
}

// Carrito global
val carritoGlobal = mutableListOf<Juego>()

// Catálogo de juegos
val catalogoItems = listOf(
    Juego("Battlefield", 15000, R.drawable.battlefield, "Descripcion Juego 1"),
    Juego("Fc 24", 20000, R.drawable.fifa, "Descripcion Juego 2"),
    Juego("Minecraft", 25000, R.drawable.minecraft, "Descripcion Juego 3"),
    Juego("Call of duty", 18000, R.drawable.callofduty, "Descripcion Juego 4")
)

// Función para agregar al carrito sin duplicados
fun agregarAlCarrito(juego: Juego) {
    val juegoExistente = carritoGlobal.find { it.nombre == juego.nombre }
    if (juegoExistente != null) {
        juegoExistente.cantidadState += 1
    } else {
        juego.cantidadState = 1
        carritoGlobal.add(juego)
    }
}

// Función para eliminar un juego del carrito
fun eliminarDelCarrito(juego: Juego) {
    carritoGlobal.remove(juego)
}

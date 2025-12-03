package com.example.gamezone.model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("categoria")
    val categoria: String? = null,
    @SerializedName("descripcion")
    val descripcion: String? = null,
    @SerializedName("precio")
    val precio: Double,
    @SerializedName("imagen")
    val imagen: String? = null
)

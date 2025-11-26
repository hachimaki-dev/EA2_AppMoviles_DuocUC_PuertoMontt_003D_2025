package com.example.duocappmoviles003d.model
import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("email")
    val email: String,
    @SerializedName("nombre")
    val nombre: String
)

data class MiembroResponse(
    @SerializedName("usuarios") val usuario: Usuario
)

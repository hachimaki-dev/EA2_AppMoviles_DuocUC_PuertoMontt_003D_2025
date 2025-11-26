package com.example.duocappmoviles003d

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id_product") val id: Long? = null, // Puede ser null al crear uno nuevo
    val name: String,
    val price: Double, // 'numeric' en DB suele ser Double en Kotlin
    @SerialName("image_url") val imageUrl: String,
    val stock: Int,
    val description: String
)

@Serializable
data class User(
    @SerialName("id_user") val id: Long? = null,
    val email: String,
    val username: String,
    // Opcional: password, aunque cuidado con manejarla en texto plano
    val password: String? = null
)
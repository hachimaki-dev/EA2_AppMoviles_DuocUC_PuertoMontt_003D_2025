package com.example.duocappmoviles003d

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("id_product") val id: Long,
    val name: String,
    val price: Double,
    @SerialName("image_url") val imageUrl: String, // Coil usará esto
    val stock: Int,
    val description: String
)
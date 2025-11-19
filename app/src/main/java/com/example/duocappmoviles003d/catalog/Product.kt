package com.example.duocappmoviles003d.catalog

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("price")
    val price: Double,

    @SerializedName("image_url")  // ← Así viene de Supabase
    val imageUrl: String,          // ← Así lo usamos en Kotlin

    @SerializedName("stock")
    val stock: Int
)
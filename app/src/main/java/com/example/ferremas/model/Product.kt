package com.example.ferremas.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("id")
    val id: String = "",

    @SerializedName("name")
    val name: String = "",

    @SerializedName("price")
    val price: Double = 0.0,

    // imageUrl es string, porque en Supabase guardas una URL
    @SerializedName("image_url")
    val imageUrl: String = "",

    @SerializedName("categoryId")
    val categoryId: String = ""
)

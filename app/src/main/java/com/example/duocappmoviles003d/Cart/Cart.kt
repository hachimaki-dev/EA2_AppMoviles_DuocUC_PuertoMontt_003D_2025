package com.example.duocappmoviles003d.Cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Cart(
    @SerialName("id_cart") val id: Long? = null,
    @SerialName("id_user") val userId: Long,
    @SerialName("total_price") val totalPrice: Double? = 0.0,
    @SerialName("gross_price") val grossPrice: Double? = 0.0,
    val status: String = "pending"
)
package com.example.duocappmoviles003d.Cart

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CartProduct(
    @SerialName("id_product") val productId: Long,
    @SerialName("id_cart") val cartId: Long,
    @SerialName("product_quantity") val quantity: Int,
    @SerialName("gross_price") val grossPrice: Double
)
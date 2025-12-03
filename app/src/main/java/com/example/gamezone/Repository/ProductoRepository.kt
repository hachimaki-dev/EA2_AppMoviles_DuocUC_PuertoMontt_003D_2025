package com.example.gamezone.Repository

import com.example.gamezone.model.Producto
import com.example.gamezone.network.RetrofitClient

class ProductoRepository {

    private val api = RetrofitClient.apiService

    suspend fun obtenerProductos(): List<Producto>? {
        return try {
            val response = api.obtenerProductos()

            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

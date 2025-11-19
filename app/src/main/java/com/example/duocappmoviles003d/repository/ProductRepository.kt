package com.example.duocappmoviles003d.repository

import com.example.duocappmoviles003d.catalog.Product
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {  // ← Cambia a hilo de red
            try {
                val response = apiService.getProducts()

                if (response.isSuccessful && response.body() != null) {
                    // ✅ Éxito
                    Result.success(response.body()!!)
                } else {
                    // ❌ Error HTTP (404, 500, etc.)
                    Result.failure(
                        Exception("Error ${response.code()}: ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                // ❌ Error de red (sin internet, timeout, etc.)
                Result.failure(e)
            }
        }
    }
}
package com.example.duocappmoviles003d.repository

import com.example.ferremas.model.Product
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProductRepository {

    private val apiService = RetrofitClient.apiService

    suspend fun getProducts(): Result<List<Product>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getProducts()

                println("🔵 API RESPONSE CODE: ${response.code()}")
                println("🔵 API RAW BODY: ${response.body()}")

                if (response.isSuccessful && response.body() != null) {
                    println("🟢 PRODUCTS OK: ${response.body()}")
                    Result.success(response.body()!!)
                } else {
                    println("🔴 ERROR API: ${response.errorBody()?.string()}")
                    Result.failure(
                        Exception("Error ${response.code()}: ${response.message()}")
                    )
                }
            } catch (e: Exception) {
                println("🔴 EXCEPTION API: ${e.localizedMessage}")
                Result.failure(e)
            }
        }
    }
}
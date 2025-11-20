package com.example.duocappmoviles003d.repository

import com.example.duocappmoviles003d.ejemplo.Ejemplo
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EjemploRepository {

    private val conexionAlApiService = RetrofitClient.apiService

    suspend fun getEjemplos(): Result<List<Ejemplo>>{
        return withContext(Dispatchers.IO) {  // ← Cambia a hilo de red
            try {
                val response = conexionAlApiService.getEjemplos()

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
package com.example.duocappmoviles003d.Repository

import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.network.RetrofitClient

class ProfileRepository {

    private val api = RetrofitClient.apiService

    suspend fun obtenerPerfil(id: Long): Usuario? {
        return try {
            val response = api.obtenerUsuarioPorId(id)

            if (response.isSuccessful) {
                val lista = response.body()
                if (!lista.isNullOrEmpty()) lista.first() else null
            } else null

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

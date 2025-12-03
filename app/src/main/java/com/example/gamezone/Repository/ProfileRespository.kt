package com.example.gamezone.Repository

import com.example.gamezone.model.Usuario
import com.example.gamezone.network.RetrofitClient

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

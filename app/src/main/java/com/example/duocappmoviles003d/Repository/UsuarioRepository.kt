package com.example.duocappmoviles003d.Repository


import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.network.RetrofitClient

class UsuarioRepository {

    private val api = RetrofitClient.apiService

    // ------------------------------------------
    // LOGIN
    // ------------------------------------------
    suspend fun validarLogin(username: String, password: String): Usuario? {
        return try {
            val response = api.validarLogin(username, password)

            if (response.isSuccessful) {
                val lista = response.body()
                if (!lista.isNullOrEmpty()) lista.first() else null
            } else null

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    // ------------------------------------------
    // REGISTRO
    // ------------------------------------------
    suspend fun registrarUsuario(usuario: Usuario): Boolean {
        return try {
            val response = api.registrarUsuario(usuario)
            response.isSuccessful

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

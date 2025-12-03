package com.example.duocappmoviles003d.Repository

import android.util.Log
import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.network.RetrofitClient

class UsuarioRepository {

    // Instancia del cliente Retrofit
    private val api = RetrofitClient.apiService

    // ------------------------------------------
    // LOGIN: Validar credenciales con Supabase
    // ------------------------------------------
    suspend fun validarLogin(username: String, password: String): Usuario? {
        return try {
            // 💡 EL TRUCO PARA SUPABASE:
            // Agregamos "eq." (equal) antes del valor.
            // Esto le dice a la base de datos: "Busca donde la columna sea IGUAL a esto".
            val response = api.validarLogin(
                username = "eq.$username",
                password = "eq.$password"
            )

            if (response.isSuccessful) {
                val listaUsuarios = response.body()

                // VALIDACIÓN DE LA RESPUESTA
                if (!listaUsuarios.isNullOrEmpty()) {
                    // 1. Si la lista TIENE datos, las credenciales son CORRECTAS.
                    Log.d("REPO_LOGIN", "✅ Usuario encontrado: ${listaUsuarios.first().username}")
                    listaUsuarios.first() // Retornamos el usuario encontrado
                } else {
                    // 2. Si la lista está VACÍA, significa que:
                    //    a) El usuario o contraseña están mal.
                    //    b) O falta la política "SELECT" en Supabase.
                    Log.e("REPO_LOGIN", "⛔ Credenciales incorrectas o usuario no encontrado (Lista vacía)")
                    null
                }
            } else {
                Log.e("REPO_LOGIN", "❌ Error del servidor: ${response.code()}")
                null
            }

        } catch (e: Exception) {
            Log.e("REPO_LOGIN", "❌ Error de conexión: ${e.message}")
            e.printStackTrace()
            null
        }
    }

    // ------------------------------------------
    // REGISTRO: Crear nuevo usuario
    // ------------------------------------------
    suspend fun registrarUsuario(usuario: Usuario): Boolean {
        return try {
            // En el registro NO usamos "eq." porque enviamos el objeto JSON en el Body
            val response = api.registrarUsuario(usuario)

            if (response.isSuccessful) {
                Log.d("REPO_REGISTRO", "✅ Registro exitoso")
                true
            } else {
                Log.e("REPO_REGISTRO", "❌ Falló el registro: ${response.code()}")
                false
            }
        } catch (e: Exception) {
            Log.e("REPO_REGISTRO", "❌ Excepción al registrar: ${e.message}")
            e.printStackTrace()
            false
        }
    }
}
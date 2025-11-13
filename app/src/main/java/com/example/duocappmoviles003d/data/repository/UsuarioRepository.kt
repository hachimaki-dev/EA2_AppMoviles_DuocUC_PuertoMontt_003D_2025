package com.example.duocappmoviles003d.data.repository

import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.model.Producto
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository {

    private val postgrest = supabase.postgrest

    // ---------------------------------------------------
    // REGISTRAR USUARIO
    // ---------------------------------------------------
    suspend fun registrarUsuario(usuario: Usuario): Boolean =
        withContext(Dispatchers.IO) {
            try {
                postgrest["usuarios"].insert(usuario)
                true
            } catch (e: Exception) {
                false
            }
        }

    // ---------------------------------------------------
    // VALIDAR LOGIN (username + password)
    // ---------------------------------------------------
    suspend fun validarLogin(username: String, password: String): Usuario? =
        withContext(Dispatchers.IO) {
            try {
                postgrest["usuarios"]
                    .select {
                        filter {
                            eq("username", username)
                            eq("password", password)
                        }
                    }
                    .decodeSingle<Usuario>()  // convierte directo al modelo
            } catch (e: Exception) {
                null
            }
        }

    // ---------------------------------------------------
    // OBTENER PRODUCTOS
    // ---------------------------------------------------
    suspend fun obtenerProductos(): List<Producto> =
        withContext(Dispatchers.IO) {
            try {
                postgrest["productos"]
                    .select()
                    .decodeList<Producto>()
            } catch (e: Exception) {
                emptyList()
            }
        }

    // ---------------------------------------------------
    // AGREGAR AL CARRITO
    // ---------------------------------------------------
    suspend fun agregarAlCarrito(
        usuarioId: Long,
        productoId: Long,
        cantidad: Int = 1
    ): Boolean = withContext(Dispatchers.IO) {

        val payload = mapOf(
            "usuario_id" to usuarioId,
            "producto_id" to productoId,
            "cantidad" to cantidad
        )

        try {
            postgrest["carrito"].insert(payload)
            true
        } catch (e: Exception) {
            false
        }
    }
    // Comprueba si existe un usuario con el email dado
    suspend fun existeEmail(email: String): Boolean = withContext(Dispatchers.IO) {
        try {
            val res = postgrest["usuarios"]
                .select {
                    filter {
                        eq("email", email)
                    }
                }
                .decodeList<Usuario>()

            return@withContext res.isNotEmpty()
        } catch (e: Exception) {
            false
        }
    }

}

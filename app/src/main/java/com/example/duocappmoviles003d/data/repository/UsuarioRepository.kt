package com.example.duocappmoviles003d.data.repository

import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.model.Producto
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UsuarioRepository(
    private val postgrest: Postgrest = supabase.postgrest
) {

    // -------------------------------------------
    // REGISTRAR USUARIO
    // -------------------------------------------
    suspend fun registrarUsuario(usuario: Usuario): Boolean = withContext(Dispatchers.IO) {
        try {
            postgrest.from("usuarios")
                .insert(usuario)

            true
        } catch (e: Exception) {
            false
        }
    }

    // -------------------------------------------
    // VALIDAR LOGIN
    // -------------------------------------------
    suspend fun validarLogin(username: String, password: String): Usuario? =
        withContext(Dispatchers.IO) {
            try {
                postgrest.from("usuarios")
                    .select()
                    .eq("username", username)
                    .eq("password", password)
                    .single()
                    .decode<Usuario>()  // convierte directo al modelo
            } catch (e: Exception) {
                null
            }
        }

    // -------------------------------------------
    // OBTENER PRODUCTOS
    // -------------------------------------------
    suspend fun obtenerProductos(): List<Producto> = withContext(Dispatchers.IO) {
        try {
            postgrest.from("productos")
                .select()
                .decode<List<Producto>>()   // lista automática
        } catch (e: Exception) {
            emptyList()
        }
    }

    // -------------------------------------------
    // AGREGAR PRODUCTO AL CARRITO
    // -------------------------------------------
    suspend fun agregarAlCarrito(usuarioId: Long, productoId: Long, cantidad: Int = 1): Boolean =
        withContext(Dispatchers.IO) {
            try {
                val payload = mapOf(
                    "usuario_id" to usuarioId,
                    "producto_id" to productoId,
                    "cantidad" to cantidad
                )

                postgrest.from("carrito").insert(payload)

                true
            } catch (e: Exception) {
                false
            }
        }
}

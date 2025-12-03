package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.model.Producto
import com.example.duocappmoviles003d.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ---------------------------
    // LOGIN
    // ---------------------------

    @GET("usuario")
    suspend fun validarLogin(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("select") select: String = "*"
    ): Response<List<Usuario>>

    // ---------------------------
    // REGISTRO
    // ---------------------------

    @POST("usuario")
    suspend fun registrarUsuario(
        @Body usuario: Usuario
    ): Response<Unit>

    // ---------------------------
    // OBTENER PRODUCTOS
    // ---------------------------

    @GET("producto")
    suspend fun obtenerProductos(
        @Query("select") select: String = "*"
    ): Response<List<Producto>>

    // ---------------------------
    // OBTENER PERFIL POR ID
    // ---------------------------

    @GET("usuario")
    suspend fun obtenerUsuarioPorId(
        @Query("id") id: Long,
        @Query("select") select: String = "*"
    ): Response<List<Usuario>>
    // -------------------------------------------
// VERIFICAR EMAIL
// -------------------------------------------

    @GET("usuario")
    suspend fun existeEmail(
        @Query("email") email: String,
        @Query("select") select: String = "id"
    ): Response<List<Usuario>>

}

package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.model.Producto
import com.example.duocappmoviles003d.model.Usuario
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ---------------------------
    // LOGIN
    // ---------------------------
    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY"
    )
    @GET("usuario")
    suspend fun validarLogin(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("select") select: String = "*"
    ): Response<List<Usuario>>

    // ---------------------------
    // REGISTRO
    // ---------------------------
    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY",
        "Content-Type: application/json"
    )
    @POST("usuario")
    suspend fun registrarUsuario(
        @Body usuario: Usuario
    ): Response<Unit>

    // ---------------------------
    // OBTENER PRODUCTOS
    // ---------------------------
    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY"
    )
    @GET("producto")
    suspend fun obtenerProductos(
        @Query("select") select: String = "*"
    ): Response<List<Producto>>

    // ---------------------------
    // OBTENER PERFIL POR ID
    // ---------------------------
    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY"
    )
    @GET("usuario")
    suspend fun obtenerUsuarioPorId(
        @Query("id") id: Long,
        @Query("select") select: String = "*"
    ): Response<List<Usuario>>
    // -------------------------------------------
// VERIFICAR EMAIL
// -------------------------------------------
    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY"
    )
    @GET("usuario")
    suspend fun existeEmail(
        @Query("email") email: String,
        @Query("select") select: String = "id"
    ): Response<List<Usuario>>

}

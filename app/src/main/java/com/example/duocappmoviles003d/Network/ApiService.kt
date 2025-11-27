package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.model.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Body

interface ApiService {

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

    @Headers(
        "apikey: TU_API_KEY",
        "Authorization: Bearer TU_API_KEY",
        "Content-Type: application/json"
    )
    @POST("usuario")
    suspend fun registrarUsuario(
        @Body usuario: Usuario
    ): Response<Unit>
}

package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.model.Producto
import com.example.duocappmoviles003d.model.Usuario
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Headers
import retrofit2.http.Query
import retrofit2.http.Body

interface ApiService {

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"
    )
    @GET("usuario")
    suspend fun validarLogin(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("select") select: String = "*"
    ): Response<List<Usuario>>

    @POST("usuario")
    suspend fun registrarUsuario(
        @Body usuario: Usuario
    ): Response<Unit>

    //Juegos
    @GET("juego")
    suspend fun getProductos(): Response<List<Producto>>

}

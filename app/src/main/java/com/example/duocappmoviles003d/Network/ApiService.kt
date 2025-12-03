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
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"
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
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
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
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"
    )
    @GET("producto")
    suspend fun obtenerProductos(
        @Query("select") select: String = "*"
    ): Response<List<Producto>>

    // ---------------------------
    // OBTENER PERFIL POR ID
    // ---------------------------
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"
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
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0.AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"
    )
    @GET("usuario")
    suspend fun existeEmail(
        @Query("email") email: String,
        @Query("select") select: String = "id"
    ): Response<List<Usuario>>

}

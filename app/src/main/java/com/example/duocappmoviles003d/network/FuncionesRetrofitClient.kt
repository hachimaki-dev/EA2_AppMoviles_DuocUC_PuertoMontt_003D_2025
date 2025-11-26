package com.example.duocappmoviles003d.network

import com.example.duocappmoviles003d.model.EmailRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Definimos la interfaz
interface FunctionsApiService {
    @Headers(
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json"
    )
    @POST("send-email")
    suspend fun sendEmail(@Body request: EmailRequest): Response<Unit>
}

object FuncionesRetrofitClient {
    // URL para la Edge Function de supabase
    private const val BASE_URL = "https://agzpnkytahlxokksalxo.supabase.co/functions/v1/"

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: FunctionsApiService = retrofit.create(FunctionsApiService::class.java)
}
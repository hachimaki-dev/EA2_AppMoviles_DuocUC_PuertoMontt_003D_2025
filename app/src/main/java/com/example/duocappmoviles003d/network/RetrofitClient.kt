package com.example.duocappmoviles003d.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // URL base de tu API Supabase
    private const val BASE_URL = "https://fxmwpkakqzfohuiyuayg.supabase.co/rest/v1/"

    // Interceptor para ver peticiones en Logcat
    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY  // Muestra request y response completos
    }

    // Cliente HTTP con el interceptor
    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)  // Agrega el logger
        .build()

    // Instancia de Retrofit configurada
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)                              // URL base
        .client(httpClient)                             // Cliente con logging
        .addConverterFactory(GsonConverterFactory.create())  // JSON → Kotlin
        .build()

    // ApiService listo para usar
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
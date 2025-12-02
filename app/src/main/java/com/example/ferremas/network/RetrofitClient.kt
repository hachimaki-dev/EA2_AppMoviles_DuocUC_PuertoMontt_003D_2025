package com.example.duocappmoviles003d.network

import ApiService
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val BASE_URL = "https://dldfashkylaikbvsrhim.supabase.co/rest/v1/"

    // ⚠️ PON AQUÍ TU API KEY REAL DE SUPABASE
    private const val API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImRsZGZhc2hreWxhaWtidnNyaGltIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjQ2MDk0NzksImV4cCI6MjA4MDE4NTQ3OX0.KApvo8IajnAEJQ8HSevImgOJ6wyvc0NhQRENHfRwEG4"

    private val headerInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("apikey", API_KEY)
            .addHeader("Authorization", "Bearer $API_KEY")
            .addHeader("Content-Type", "application/json")
            .build()
        chain.proceed(request)
    }

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(headerInterceptor)   // 📌 Headers para Supabase
        .addInterceptor(loggingInterceptor)  // 📌 Log de peticiones
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(httpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}

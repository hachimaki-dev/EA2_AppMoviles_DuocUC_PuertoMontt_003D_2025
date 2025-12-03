package com.example.duocappmoviles003d.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {

    // 1. URL DEL PROYECTO (Verificada con tu log)
    private const val BASE_URL = "https://yqsjpbuxfbbpjcmqmpec.supabase.co/rest/v1/"

    // 2. CLAVE PARTIDA (Técnica anti-errores de copiado)
    // Al dividirla, eliminamos cualquier caracter basura oculto en la línea larga.
    private const val KEY_PART_1 = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inlxc2pwYnV4ZmJicGpjbXFtcGVjIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1MTgzMjUsImV4cCI6MjA3OTA5NDMyNX0"
    private const val KEY_PART_2 = ".AoqRzYoDC249TvMU_EnVimqOCFY4NY9HG7G9wtbs7qY"

    // Unimos las partes y limpiamos
    private val SUPABASE_KEY = (KEY_PART_1 + KEY_PART_2).trim()

    // 3. Cliente HTTP con Interceptor
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                // Borra cualquier header anterior para evitar duplicados
                .header("apikey", SUPABASE_KEY)
                .header("Authorization", "Bearer $SUPABASE_KEY")
                .header("Prefer", "return=minimal") // ¡ESTE ES VITAL!
                .header("User-Agent", "DuocApp/1.0") // Ayuda a que Cloudflare no nos bloquee
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    // 4. Instancia Retrofit
    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
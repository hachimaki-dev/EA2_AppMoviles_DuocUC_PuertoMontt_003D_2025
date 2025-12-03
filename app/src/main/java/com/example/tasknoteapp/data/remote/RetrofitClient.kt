package com.example.tasknoteapp.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    
    // IMPORTANTE: Reemplaza esto con tu URL base de Supabase
    // Debe terminar en /rest/v1/
    private const val BASE_URL = "https://dwskatwlkqsgqfbjihpu.supabase.co/rest/v1/"

    // IMPORTANTE: Reemplaza esto con tu API Key (anon key) de Supabase
    private const val API_KEY = "sb_publishable_QY4AYcrKc-xLQJfPoxwDpA__8loxrwD"

    val api: SupabaseApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                okhttp3.OkHttpClient.Builder().addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("apikey", API_KEY)
                        .addHeader("Authorization", "Bearer $API_KEY")
                        .addHeader("Content-Type", "application/json")
                        .build()
                    chain.proceed(request)
                }.build()
            )
            .build()

        retrofit.create(SupabaseApi::class.java)
    }
}

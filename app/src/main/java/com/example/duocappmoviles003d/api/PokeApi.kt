package com.example.duocappmoviles003d.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PokeApiService::class.java)

    }
}
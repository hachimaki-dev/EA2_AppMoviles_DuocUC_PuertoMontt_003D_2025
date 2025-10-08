package com.example.duocappmoviles003d.service

import com.example.duocappmoviles003d.models.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

interface PokeApi {
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(@Path("name") name: String): Pokemon
}


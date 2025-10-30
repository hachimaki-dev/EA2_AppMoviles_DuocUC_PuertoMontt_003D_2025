package com.example.duocappmoviles003d.api

import com.example.duocappmoviles003d.model.PokemonResponse
import retrofit2.http.GET

interface PokeApiService {
    @GET("pokemon?limit=150") //Con esto mostraremos los primeros 150 Pokemon :)
    suspend fun getPokemonList(): PokemonResponse

}
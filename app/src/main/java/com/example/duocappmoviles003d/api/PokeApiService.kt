package com.example.duocappmoviles003d.api

import com.example.duocappmoviles003d.model.PokemonDetailDto
import com.example.duocappmoviles003d.model.PokemonResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Path
import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {


    @GET("pokemon") //Con esto mostraremos los primeros 150 Pokemon :)
    suspend fun getPokemonList(
        @Query("limit") limit: Int = 30,
        @Query("offset") offset: Int = 0

    ): PokemonResponse

    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(
        @Path("id") name: String): PokemonDetailDto

    companion object {
        private const val BASE_URL = "https://pokeapi.co/api/v2/"

        fun create(): PokeApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(PokeApiService::class.java)

        }
    }
}


package com.example.duocappmoviles003d.model

data class PokemonResponse(
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
)

data class Pokemon(
    val name: String,
    val imageUrl: String
)
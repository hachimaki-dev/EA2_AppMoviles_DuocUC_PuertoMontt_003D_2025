package com.example.duocappmoviles003d.model

// DTOs que vienen de la API
data class PokemonResponse(
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
)

// DTO de detalle para obtener sprites
data class PokemonDetailDto(
    val name: String,
    val sprites: SpritesDto
)

data class SpritesDto(
    val front_default: String?
)

// Modelo que usará la UI
data class Pokemon(
    val name: String,
    val imageUrl: String
)


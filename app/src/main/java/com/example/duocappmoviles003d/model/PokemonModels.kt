package com.example.duocappmoviles003d.model

// DTOs que vienen de la API
data class PokemonResponse(
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
)

// DTO de detalle para obtener sprites, tipos y stats
data class PokemonDetailDto(
    val name: String,
    val sprites: SpritesDto,
    val types: List<TypeSlotDto>,
    val stats: List<StatSlotDto>
)

// Estructura de sprites
data class SpritesDto(
    val front_default: String?,
    val other: OtherSpritesDto? = null
)

data class OtherSpritesDto(
    val official_artwork: OfficialArtworkDto? = null
)

data class OfficialArtworkDto(
    val front_default: String?
)

// Estructura de tipos
data class TypeSlotDto(
    val slot: Int,
    val type: TypeDto
)

data class TypeDto(
    val name: String
)

// Estructura de estadísticas
data class StatSlotDto(
    val base_stat: Int,
    val stat: StatDto
)

data class StatDto(
    val name: String
)

// Modelo que usará la UI
data class Pokemon(
    val name: String,
    val imageUrl: String
)

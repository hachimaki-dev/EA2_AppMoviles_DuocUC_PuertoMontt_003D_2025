package com.example.duocappmoviles003d.model

// DTOs que vienen de la API
data class PokemonResponse(
    val results: List<PokemonDto>
)

data class PokemonDto(
    val name: String,
    val url: String
)

// Modelo que usara la UI
data class Pokemon(
    val name: String,
    val imageUrl: String
)

// Función de extensión para mapear explícitamente DTO -> Modelo
fun List<PokemonDto>.toPokemonList(): List<Pokemon> {
    return this.map { dto: PokemonDto ->
        // extraer el id desde la URL de detalle del pokemon :)
        val id = dto.url.trimEnd('/').substringAfterLast('/')
        Pokemon(
            name = dto.name.replaceFirstChar { it.uppercase() },
            imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
        )
    }
}

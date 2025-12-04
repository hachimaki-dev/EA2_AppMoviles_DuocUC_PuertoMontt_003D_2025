package com.example.duocappmoviles003d.model

/**
 * Estado completo de la pantalla de Pokédex.
 */
data class PokedexUiState(
    val isLoading: Boolean = false,

    // Lista completa que viene de la API
    val allPokemon: List<Pokemon> = emptyList(),

    // Lista que realmente se muestra en la grilla
    val visiblePokemon: List<Pokemon> = emptyList(),

    // Búsqueda
    val searchQuery: String = "",

    // Filtro por tipo (null = todos)
    val selectedType: String? = null,
    val availableTypes: List<String> = emptyList(),

    // Paginación en memoria
    val currentPage: Int = 0,
    val pageSize: Int = 18, // por ejemplo: 3 columnas x 6 filas
    val totalPages: Int = 0,

    val errorMessage: String? = null
)

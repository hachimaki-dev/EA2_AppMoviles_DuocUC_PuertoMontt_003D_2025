package com.example.duocappmoviles003d.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.api.PokeApiService
import com.example.duocappmoviles003d.model.PokedexUiState
import com.example.duocappmoviles003d.model.Pokemon
import com.example.duocappmoviles003d.model.PokemonDetail
import com.example.duocappmoviles003d.model.Stat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    // Servicio de la API
    private val api = PokeApiService.create()

    // ==================== LISTA / CATÁLOGO ====================

    private val _uiState = MutableStateFlow(PokedexUiState(isLoading = true))
    val uiState: StateFlow<PokedexUiState> = _uiState

    // ==================== DETALLE ====================

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    init {
        fetchPokemonList()
    }

    // ---------- Carga de lista de Pokémon ----------

    private fun fetchPokemonList(limit: Int = 151) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                // 1. Obtener la lista básica
                val listResponse = api.getPokemonList(limit = limit)

                // 2. Para cada Pokémon, obtener su detalle para tipos e imagen
                val pokemons = listResponse.results.map { dto ->
                    val detail = api.getPokemonDetail(dto.name)

                    val id = extractIdFromUrl(dto.url)

                    val imageUrl =
                        detail.sprites.other?.official_artwork?.front_default
                            ?: detail.sprites.front_default
                            ?: "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"

                    val types = detail.types
                        .sortedBy { it.slot }
                        .map { it.type.name }

                    Pokemon(
                        id = id,
                        name = detail.name.replaceFirstChar { it.uppercase() },
                        imageUrl = imageUrl,
                        types = types
                    )
                }.sortedBy { it.id }

                val typesDistinct = pokemons
                    .flatMap { it.types }
                    .distinct()
                    .sorted()

                _uiState.update {
                    it.copy(
                        isLoading = false,
                        allPokemon = pokemons,
                        availableTypes = typesDistinct,
                        errorMessage = null
                    )
                }

                recomputeVisibleList()
            } catch (e: Exception) {
                Log.e("PokedexViewModel", "Error al cargar la lista de Pokémon", e)
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        errorMessage = "Error al cargar la Pokédex: ${e.message}"
                    )
                }
            }
        }
    }

    /**
     * Extrae el ID desde la URL de la PokeAPI.
     */
    private fun extractIdFromUrl(url: String): Int {
        return url.trimEnd('/')
            .substringAfterLast('/')
            .toIntOrNull() ?: 0
    }

    // ---------- Filtros, búsqueda y paginación ----------

    fun onSearchQueryChange(newQuery: String) {
        _uiState.update {
            it.copy(
                searchQuery = newQuery,
                currentPage = 0
            )
        }
        recomputeVisibleList()
    }

    fun onTypeSelected(type: String?) {
        _uiState.update {
            it.copy(
                selectedType = type,
                currentPage = 0
            )
        }
        recomputeVisibleList()
    }

    fun nextPage() {
        _uiState.update { state ->
            val lastPage = (state.totalPages - 1).coerceAtLeast(0)
            state.copy(currentPage = (state.currentPage + 1).coerceAtMost(lastPage))
        }
        recomputeVisibleList()
    }

    fun previousPage() {
        _uiState.update { state ->
            state.copy(currentPage = (state.currentPage - 1).coerceAtLeast(0))
        }
        recomputeVisibleList()
    }

    private fun recomputeVisibleList() {
        val state = _uiState.value

        // 1. Filtrar por tipo + búsqueda
        val filtered = state.allPokemon.filter { pokemon ->
            val matchesType = state.selectedType == null ||
                    state.selectedType in pokemon.types

            val query = state.searchQuery.trim()

            val matchesSearch =
                if (query.isBlank()) {
                    true
                } else if (query.all { it.isDigit() }) {
                    // búsqueda por número
                    pokemon.id.toString() == query
                } else {
                    // búsqueda por nombre
                    pokemon.name.contains(query, ignoreCase = true)
                }

            matchesType && matchesSearch
        }

        // 2. Paginación
        val totalPages =
            if (filtered.isEmpty()) 0
            else (filtered.size - 1) / state.pageSize + 1

        val currentPage = state.currentPage.coerceIn(
            minimumValue = 0,
            maximumValue = (totalPages - 1).coerceAtLeast(0)
        )

        val fromIndex = currentPage * state.pageSize
        val toIndex = (fromIndex + state.pageSize).coerceAtMost(filtered.size)

        val pageItems = if (fromIndex in filtered.indices) {
            filtered.subList(fromIndex, toIndex)
        } else {
            emptyList()
        }

        _uiState.update {
            it.copy(
                visiblePokemon = pageItems,
                totalPages = totalPages,
                currentPage = currentPage
            )
        }
    }

    // ---------- Detalle de Pokémon  ----------

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val detailResponse = api.getPokemonDetail(name)
                val types = detailResponse.types.map { it.type.name }
                val stats = detailResponse.stats.map { Stat(it.stat.name, it.base_stat) }
                val imageUrl =
                    detailResponse.sprites.other?.official_artwork?.front_default
                        ?: detailResponse.sprites.front_default
                        ?: ""

                _pokemonDetail.value = PokemonDetail(
                    name = detailResponse.name.replaceFirstChar { it.uppercase() },
                    imageUrl = imageUrl,
                    types = types,
                    stats = stats
                )
            } catch (e: Exception) {
                Log.e("PokedexViewModel", "Error al cargar detalle de $name", e)
            }
        }
    }
}

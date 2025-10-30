package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Pokemon
import com.example.duocappmoviles003d.model.PokemonDto
import com.example.duocappmoviles003d.api.PokeApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    private val api = PokeApiService.create()

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val listResponse = api.getPokemonList()
                val pokemons = listResponse.results.map { dto ->
                    // Llamamos al detalle para obtener el sprite
                    val detail = api.getPokemonDetail(dto.url.trimEnd('/').substringAfterLast("/"))
                    Pokemon(
                        name = dto.name.replaceFirstChar { it.uppercase() },
                        imageUrl = detail.sprites.front_default ?: ""
                    )
                }
                _pokemonList.value = pokemons
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

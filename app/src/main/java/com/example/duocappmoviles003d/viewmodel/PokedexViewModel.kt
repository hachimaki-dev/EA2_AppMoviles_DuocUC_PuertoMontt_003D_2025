package com.example.duocappmoviles003d.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.api.PokeApiService
import com.example.duocappmoviles003d.model.Pokemon
import com.example.duocappmoviles003d.model.PokemonDetail
import com.example.duocappmoviles003d.model.Stat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {

    private val api = PokeApiService.create()

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                val listResponse = api.getPokemonList(limit = 30)
                val pokemons = listResponse.results.map { dto ->
                    val id = dto.url.trimEnd('/').substringAfterLast('/')
                    Pokemon(
                        name = dto.name.replaceFirstChar { it.uppercase() },
                        imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
                    )
                }
                _pokemonList.value = pokemons
            } catch (e: Exception) {
                Log.e("PokedexViewModel", "Error al cargar la lista de Pokémon", e)
            }
        }
    }

    fun loadPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                val detailResponse = api.getPokemonDetail(name)
                val types = detailResponse.types.map { it.type.name }
                val stats = detailResponse.stats.map { Stat(it.stat.name, it.base_stat) }
                val imageUrl = detailResponse.sprites.front_default ?: ""

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


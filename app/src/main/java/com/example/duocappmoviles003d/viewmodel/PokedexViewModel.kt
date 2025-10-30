package com.example.duocappmoviles003d.viewmodel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Pokemon
import com.example.duocappmoviles003d.model.PokemonResponse
import com.example.duocappmoviles003d.model.toPokemonList
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// --- API Service ---
interface PokeApiService {
    @GET("pokemon?limit=151")
    suspend fun getPokemonList(): PokemonResponse
}

// --- ViewModel ---
class PokedexViewModel : ViewModel() {
    var pokemonList = mutableStateListOf<Pokemon>()
        private set

    private val api = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PokeApiService::class.java)

    init {
        fetchPokemon()
    }

    private fun fetchPokemon() {
        viewModelScope.launch {
            try {
                val response = api.getPokemonList()
                pokemonList.addAll(response.results.toPokemonList())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

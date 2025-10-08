package com.example.duocappmoviles003d.service

import com.example.duocappmoviles003d.models.Pokemon
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed interface EstadoUIPoke {
    data class Success(val pokemon: Pokemon) : EstadoUIPoke
    object Error : EstadoUIPoke
    object Cargando : EstadoUIPoke
}

class PokemonModelView: ViewModel(){

    var estadoUIPoke: EstadoUIPoke by mutableStateOf(EstadoUIPoke.Cargando)
        private set

    fun getPokemon(nombre: String){
        viewModelScope
    }

}

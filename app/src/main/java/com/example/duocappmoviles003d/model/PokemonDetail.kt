package com.example.duocappmoviles003d.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

//Modelo que usara la UI de detalle :)

@Parcelize
data class PokemonDetail(
    val name: String,
    val imageUrl: String,
    val types: List<String>, // Lista de tipos del Pokémon
    val stats: List<Stat>   // Lista de estadísticas del Pokémon
): Parcelable
//Modelo para cada Stat :)
@Parcelize
data class Stat(
    val name: String, //Nombre del Stat como el HP, Attack, etc.
    val value: Int    //Valor del Stat como el valor del HP, Attack, etc.
): Parcelable

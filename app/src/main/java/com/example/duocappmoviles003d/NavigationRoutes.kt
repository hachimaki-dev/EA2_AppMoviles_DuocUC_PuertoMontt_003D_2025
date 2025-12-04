package com.example.duocappmoviles003d

import android.R.attr.id

object NavigationRoutes {
    //Auth
    const val LOGIN = "login"
    const val REGISTER = "register"
    //Pokedex
    const val POKEDEX_HOME = "pokedex_home"

    //Detalle del Pokemon por nombre
    const val POKE_DETAIL = "pokemon_detail/{name}"
    //Funcion para incluir la ruta dinamica
    fun pokemonDetailRoute(name: String) = "pokemon_detail/$name"

}

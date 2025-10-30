package com.example.duocappmoviles003d

object NavigationRoutes {
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val POKEDEX_HOME = "pokedex_home"

    const val POKE_DETAIL = "pokemon_detail"
    //Funcion para incluir la ruta dinamica
    fun pokemonDetailRoute(name: String) = "$POKE_DETAIL/$name"

}

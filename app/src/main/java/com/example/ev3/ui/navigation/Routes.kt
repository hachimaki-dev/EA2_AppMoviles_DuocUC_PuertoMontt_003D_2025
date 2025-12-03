package com.example.ev3.ui.navigation

object Routes {
    const val LOGIN = "login"
    const val SIGN_UP = "sign_up"
    const val MAIN = "main"
    const val CONTACTO = "contacto"
    const val NOSOTROS = "nosotros"
    const val DIGIMON_DETAIL = "digimon_detail/{digimonId}"

    fun digimonDetail(digimonId: Int) = "digimon_detail/$digimonId"
}


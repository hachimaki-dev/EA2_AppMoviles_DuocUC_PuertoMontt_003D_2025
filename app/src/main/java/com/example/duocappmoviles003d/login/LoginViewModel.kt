package com.example.duocappmoviles003d.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel()  {
    private val _estoRepresentaAlModeloDeDatos = mutableStateOf(LoginUIState())

    val estoRepresentaAlModeloDeDatos : State<LoginUIState> = _estoRepresentaAlModeloDeDatos

    fun cuandoElEmailCambia(email : String){
        _estoRepresentaAlModeloDeDatos.value = _estoRepresentaAlModeloDeDatos.value.copy(email = email)
    }
    fun saludar(texto : String){
        println("hola, $texto cómo estas")
    }
}
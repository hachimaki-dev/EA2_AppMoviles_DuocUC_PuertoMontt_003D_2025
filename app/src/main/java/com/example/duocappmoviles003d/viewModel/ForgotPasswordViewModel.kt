package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ForgotPasswordViewModel : ViewModel() {

    private val _mensaje = MutableStateFlow("")
    val mensaje: StateFlow<String> = _mensaje

    fun enviarRecuperacion(email: String) {
        _mensaje.value = "Si el correo existe, recibirá instrucciones."
    }
}

package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun login() {
        viewModelScope.launch {

            if (username.value.isBlank() || password.value.isBlank()) {
                _error.value = "Completa todos los campos"
                return@launch
            }

            // Simulación de login correcto
            if (username.value == "admin" && password.value == "1234") {
                _loginState.value = true
            } else {
                _error.value = "Credenciales incorrectas"
            }
        }
    }
}

package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.Repository.UsuarioRepository
import com.example.duocappmoviles003d.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    val username = MutableStateFlow("")
    val password = MutableStateFlow("")

    private val _loginState = MutableStateFlow<Usuario?>(null)
    val loginState: StateFlow<Usuario?> = _loginState

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // ------------------------
    // LOGIN
    // ------------------------
    fun login() {
        viewModelScope.launch {

            if (username.value.isEmpty() || password.value.isEmpty()) {
                _error.value = "Debe ingresar usuario y contraseña"
                return@launch
            }

            val user = repo.validarLogin(username.value, password.value)

            if (user != null) {
                _loginState.value = user
                _error.value = null
            } else {
                _error.value = "Usuario o contraseña incorrectos"
            }
        }
    }
}

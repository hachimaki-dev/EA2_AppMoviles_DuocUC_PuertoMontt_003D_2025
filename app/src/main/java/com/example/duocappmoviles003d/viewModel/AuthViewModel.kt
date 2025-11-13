package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import com.example.duocappmoviles003d.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repo: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _loginState = MutableStateFlow<Usuario?>(null)
    val loginState: StateFlow<Usuario?> = _loginState

    private val _registroExitoso = MutableStateFlow(false)
    val registroExitoso: StateFlow<Boolean> = _registroExitoso

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    // ------------------------
    // LOGIN
    // ------------------------
    fun login(username: String, password: String) {
        viewModelScope.launch {
            val user = repo.validarLogin(username, password)

            if (user != null) {
                _loginState.value = user
                _error.value = null
            } else {
                _error.value = "Usuario o contraseña incorrectos"
            }
        }
    }

    // ------------------------
    // REGISTRO
    // ------------------------
    fun registrar(usuario: Usuario) {
        viewModelScope.launch {
            val ok = repo.registrarUsuario(usuario)

            if (ok) {
                _registroExitoso.value = true
                _error.value = null
            } else {
                _registroExitoso.value = false
                _error.value = "El usuario ya existe"
            }
        }
    }
}

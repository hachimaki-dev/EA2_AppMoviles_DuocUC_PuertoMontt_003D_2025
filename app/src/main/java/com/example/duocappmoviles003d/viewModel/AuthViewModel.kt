package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.Repository.UsuarioRepository // Asegúrate que este import sea correcto
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

    // 1. Instanciamos el repositorio que conecta con Supabase
    private val repository = UsuarioRepository()

    fun login() {
        viewModelScope.launch {
            _error.value = null // Limpiamos errores previos

            if (username.value.isBlank() || password.value.isBlank()) {
                _error.value = "Completa todos los campos"
                return@launch
            }

            try {
                // 2. LLAMADA REAL A SUPABASE
                // Esto ejecutará el GET .../usuario?username=eq.tal&password=eq.tal
                val usuarioEncontrado = repository.validarLogin(username.value, password.value)

                if (usuarioEncontrado != null) {
                    // ¡Éxito! El usuario existe y la contraseña coincide
                    _loginState.value = true
                    // TIP: Aquí podrías guardar el ID del usuario en una variable global o DataStore
                    // para usarlo luego en el carrito.
                } else {
                    // Fallo: Supabase devolvió lista vacía o error
                    _error.value = "Credenciales incorrectas o error de conexión"
                    _loginState.value = false
                }
            } catch (e: Exception) {
                _error.value = "Error crítico: ${e.message}"
                _loginState.value = false
            }
        }
    }
}
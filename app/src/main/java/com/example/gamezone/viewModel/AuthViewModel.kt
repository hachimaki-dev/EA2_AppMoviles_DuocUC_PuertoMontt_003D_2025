package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gamezone.Repository.UsuarioRepository
import com.example.gamezone.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // Instancia del repositorio
    private val repository = UsuarioRepository()

    // --- ESTADOS DE DATOS DEL USUARIO ---

    // Aquí guardaremos el objeto Usuario completo cuando el login sea exitoso.
    // La pantalla de Perfil observará esta variable.
    private val _currentUser = MutableStateFlow<Usuario?>(null)
    val currentUser: StateFlow<Usuario?> = _currentUser

    // --- ESTADOS DE LA UI (Login) ---

    // null = estado inicial, true = login exitoso, false = login fallido
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    // Mensajes de error para mostrar en rojo
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Indicador de carga
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // --- FUNCIÓN DE LOGIN ---
    fun login(user: String, pass: String) {
        viewModelScope.launch {
            // 1. Limpieza inicial
            _error.value = null
            _loginState.value = null

            // 2. Validación local
            if (user.isBlank() || pass.isBlank()) {
                _error.value = "Por favor completa todos los campos"
                _loginState.value = false
                return@launch
            }

            // 3. Inicio de carga
            _isLoading.value = true

            try {
                // 4. Llamada a Supabase
                // El repositorio usa "eq." para buscar el usuario exacto
                val usuarioEncontrado = repository.validarLogin(user, pass)

                if (usuarioEncontrado != null) {
                    // ✅ ÉXITO:
                    // a) Guardamos el usuario en memoria para el Perfil
                    _currentUser.value = usuarioEncontrado
                    // b) Autorizamos la entrada
                    _loginState.value = true
                } else {
                    // ⛔ FALLO: Credenciales incorrectas
                    _error.value = "Usuario o contraseña incorrectos"
                    _loginState.value = false
                }

            } catch (e: Exception) {
                // ⛔ ERROR TÉCNICO
                _error.value = "Error de conexión: Verifica tu internet"
                _loginState.value = false
                e.printStackTrace()
            } finally {
                // 5. Fin de carga
                _isLoading.value = false
            }
        }
    }

    // --- FUNCIÓN DE CERRAR SESIÓN ---
    fun logout() {
        _currentUser.value = null // Borramos los datos del usuario
        _loginState.value = null  // Reseteamos el estado de login
    }

    // --- UTILIDAD ---
    fun resetLoginState() {
        _loginState.value = null
        _error.value = null
    }
}
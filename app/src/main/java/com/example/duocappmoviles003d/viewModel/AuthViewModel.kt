package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.Repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    // Instancia del repositorio (asegúrate de tener el import correcto arriba)
    private val repository = UsuarioRepository()

    // --- ESTADOS DE LA UI ---

    // null = estado inicial, true = login exitoso, false = login fallido
    private val _loginState = MutableStateFlow<Boolean?>(null)
    val loginState: StateFlow<Boolean?> = _loginState

    // Mensajes de error para mostrar en rojo en la pantalla
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    // Indicador de carga (para mostrar un circulito girando si quieres)
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    // --- FUNCIÓN DE LOGIN ---
    fun login(user: String, pass: String) {
        viewModelScope.launch {
            // 1. Limpieza inicial
            _error.value = null
            _loginState.value = null // Reseteamos para que no navegue solo

            // 2. Validación local rápida (ahorra datos)
            if (user.isBlank() || pass.isBlank()) {
                _error.value = "Por favor completa todos los campos"
                _loginState.value = false
                return@launch
            }

            // 3. Inicio de carga
            _isLoading.value = true

            try {
                // 4. Llamada al Repositorio (Supabase)
                // El repositorio se encarga de poner el "eq." necesario
                val usuarioEncontrado = repository.validarLogin(user, pass)

                if (usuarioEncontrado != null) {
                    // ✅ ÉXITO: El usuario existe y la contraseña coincide
                    _loginState.value = true
                } else {
                    // ⛔ FALLO: Credenciales incorrectas o usuario no existe
                    _error.value = "Usuario o contraseña incorrectos"
                    _loginState.value = false
                }

            } catch (e: Exception) {
                // ⛔ ERROR TÉCNICO: Problema de red o servidor
                _error.value = "Error de conexión: Verifica tu internet"
                _loginState.value = false
                e.printStackTrace()
            } finally {
                // 5. Fin de carga (siempre se ejecuta al final)
                _isLoading.value = false
            }
        }
    }

    // --- UTILIDADES ---

    // Llama a esto desde la UI después de navegar para limpiar el estado
    // y evitar que al volver atrás te re-dirija automáticamente.
    fun resetLoginState() {
        _loginState.value = null
        _error.value = null
    }
}
package com.example.duocappmoviles003d.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val email: String = "",
    val password: String = "",
    val mensaje: String = ""
)

class LoginViewModel(
    private val repository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun onPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(password = newValue)
    }

    fun iniciarSesion(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val email = _uiState.value.email
            val password = _uiState.value.password

            if (email.isBlank() || password.isBlank()) {
                _uiState.value = _uiState.value.copy(mensaje = "Completa todos los campos")
                return@launch
            }

            val result = repository.iniciarSesion(email, password)
            result.onSuccess {
                _uiState.value = _uiState.value.copy(mensaje = "Inicio de sesión exitoso ✅")
                onSuccess()
            }.onFailure {
                _uiState.value = _uiState.value.copy(mensaje = it.message ?: "Error desconocido")
            }
        }
    }
}

package com.example.duocappmoviles003d.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val mensaje: String = ""
)

class RegisterViewModel(
    private val repository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun onPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(password = newValue)
    }

    fun onConfirmPasswordChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = newValue)
    }

    fun registrarUsuario(onSuccess: () -> Unit) {
        viewModelScope.launch {
            val state = _uiState.value

            if (state.email.isBlank() || state.password.isBlank() || state.confirmPassword.isBlank()) {
                _uiState.value = _uiState.value.copy(mensaje = "Completa todos los campos")
                return@launch
            }

            if (state.password != state.confirmPassword) {
                _uiState.value = _uiState.value.copy(mensaje = "Las contraseñas no coinciden")
                return@launch
            }

            val result = repository.registrarUsuario(state.email, state.password)
            result.onSuccess {
                _uiState.value = _uiState.value.copy(mensaje = "Registro exitoso ")
                onSuccess()
            }.onFailure {
                _uiState.value = _uiState.value.copy(mensaje = it.message ?: "Error desconocido")
            }
        }
    }
}

package com.example.duocappmoviles003d.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ForgotPasswordUiState(
    val email: String = "",
    val mensaje: String = ""
)

class ForgotPasswordViewModel(
    private val repository: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotPasswordUiState())
    val uiState: StateFlow<ForgotPasswordUiState> = _uiState

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun enviarRecuperacion() {
        viewModelScope.launch {
            val email = _uiState.value.email
            if (email.isBlank()) {
                _uiState.value = _uiState.value.copy(mensaje = "Ingresa un correo válido")
                return@launch
            }

            val result = repository.recuperarPassword(email)
            result.onSuccess {
                _uiState.value = _uiState.value.copy(mensaje = "Correo enviado ✅")
            }.onFailure {
                _uiState.value = _uiState.value.copy(mensaje = it.message ?: "Error al enviar correo")
            }
        }
    }
}

package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.data.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ForgotUiState(
    val email: String = "",
    val mensaje: String = "",
    val loading: Boolean = false
)

class ForgotPasswordViewModel(
    private val repo: UsuarioRepository = UsuarioRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ForgotUiState())
    val uiState: StateFlow<ForgotUiState> = _uiState

    fun onEmailChanged(newEmail: String) {
        _uiState.value = _uiState.value.copy(email = newEmail, mensaje = "")
    }

    fun enviarRecuperacion(onResult: ((Boolean, String) -> Unit)? = null) {
        val email = _uiState.value.email.trim()
        if (email.isEmpty()) {
            _uiState.value = _uiState.value.copy(mensaje = "Ingresa un correo válido")
            onResult?.invoke(false, "Ingresa un correo válido")
            return
        }

        _uiState.value = _uiState.value.copy(loading = true, mensaje = "")

        viewModelScope.launch {
            try {
                // Con PostgREST no hay envío de correo nativo; aquí comprobamos existencia del email.
                val existe = repo.existeEmail(email)
                if (existe) {
                    // Aquí podrías llamar a una Function/Edge function para enviar el correo real.
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        mensaje = "Correo de recuperación (simulado) enviado a $email"
                    )
                    onResult?.invoke(true, "Correo de recuperación enviado")
                } else {
                    _uiState.value = _uiState.value.copy(
                        loading = false,
                        mensaje = "No existe una cuenta con ese correo"
                    )
                    onResult?.invoke(false, "No existe una cuenta con ese correo")
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    loading = false,
                    mensaje = "Error al procesar la solicitud"
                )
                onResult?.invoke(false, "Error al procesar la solicitud")
            }
        }
    }
}

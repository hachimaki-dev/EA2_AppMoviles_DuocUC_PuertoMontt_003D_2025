package com.example.duocappmoviles003d.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class ProfileUiState(
    val username: String = "",
    val email: String = "",
    val mensaje: String = ""
)

class ProfileViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(ProfileUiState())
    val uiState: StateFlow<ProfileUiState> = _uiState

    fun onUsernameChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(username = newValue)
    }

    fun onEmailChanged(newValue: String) {
        _uiState.value = _uiState.value.copy(email = newValue)
    }

    fun guardarCambios() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(mensaje = "Cambios guardados correctamente ✅")
        }
    }
}

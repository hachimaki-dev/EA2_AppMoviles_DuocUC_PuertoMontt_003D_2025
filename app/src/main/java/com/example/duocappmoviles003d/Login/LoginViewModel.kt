package com.example.duocappmoviles003d.Login
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.SupabaseRepository
import com.example.duocappmoviles003d.UserSession
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class LoginUiState {
    object Idle : LoginUiState()
    object Loading : LoginUiState()
    object Success : LoginUiState()
    data class Error(val message: String) : LoginUiState()
}

class LoginViewModel : ViewModel() {
    private val repository = SupabaseRepository()
    private val _uiState = MutableStateFlow<LoginUiState>(LoginUiState.Idle)
    val uiState: StateFlow<LoginUiState> = _uiState

    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _uiState.value = LoginUiState.Error("Campos vacíos")
            return
        }

        viewModelScope.launch {
            _uiState.value = LoginUiState.Loading
            val user = repository.getUserByEmail(email)

            if (user != null && user.password == password) {
                UserSession.currentUser = user // Guardamos sesión
                _uiState.value = LoginUiState.Success
            } else {
                _uiState.value = LoginUiState.Error("Credenciales incorrectas")
            }
        }
    }
}

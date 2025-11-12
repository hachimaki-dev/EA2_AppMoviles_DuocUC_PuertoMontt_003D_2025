package com.example.duocappmoviles003d.signup

import androidx.compose.runtime.Recomposer
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {

    private val _uiState = mutableStateOf(SignUpUiState())


    val uiState: State<SignUpUiState> = _uiState


    fun onNameChange(value: String) {
        _uiState.value = _uiState.value.copy(name = value)

        _uiState.value = _uiState.value.copy(
            errorMessage = if (value.isNotEmpty()) {
                "Email inválido"
            } else null
        )
    }

    fun onEmailChange(value: String){
        _uiState.value = _uiState.value.copy(email = value)
    }

    fun onPasswordChange(value: String){
        _uiState.value = _uiState.value.copy(password = value)
    }

}
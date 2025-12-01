package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.duocappmoviles003d.model.Usuario
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegistrarViewModel : ViewModel() {

    val username = MutableStateFlow("")
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    private val _registerState = MutableStateFlow<Boolean?>(null)
    val registerState: StateFlow<Boolean?> = _registerState

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun registrarUsuario() {
        val user = username.value.trim()
        val mail = email.value.trim()
        val pass = password.value.trim()
        val pass2 = confirmPassword.value.trim()

        if (user.isEmpty() || mail.isEmpty() || pass.isEmpty() || pass2.isEmpty()) {
            _error.value = "Complete todos los campos"
            return
        }

        if (pass != pass2) {
            _error.value = "Las contraseñas no coinciden"
            return
        }

        val nuevoUsuario = Usuario(
            username = user,
            email = mail,
            password = pass
        )

        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.registrarUsuario(nuevoUsuario)

                if (response.isSuccessful) {
                    _registerState.value = true
                } else {
                    _error.value = "Error al registrar. Verifique datos."
                }

            } catch (e: Exception) {
                _error.value = "Error de conexión"
            }
        }
    }
}

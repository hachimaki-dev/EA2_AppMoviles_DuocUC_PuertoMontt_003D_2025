package com.example.gamezone.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

data class User(
    val username: String = "",
    val email: String = ""
)

class ProfileViewModel : ViewModel() {

    private val _user = MutableStateFlow(User())
    val user: StateFlow<User> = _user

    init {
        // Simulación de usuario cargado
        _user.value = User(
            username = "Juan Perez",
            email = "juan@example.com"
        )
    }
}

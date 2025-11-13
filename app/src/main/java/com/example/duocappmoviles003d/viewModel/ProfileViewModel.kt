package com.example.duocappmoviles003d.viewmodel

import androidx.lifecycle.ViewModel
import com.example.duocappmoviles003d.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProfileViewModel : ViewModel() {

    private val _usuario = MutableStateFlow<Usuario?>(null)
    val usuario: StateFlow<Usuario?> = _usuario

    fun setUsuario(user: Usuario?) {
        _usuario.value = user
    }

    fun logout() {
        _usuario.value = null
    }
}

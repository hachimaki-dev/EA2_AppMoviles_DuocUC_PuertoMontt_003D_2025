package com.example.gamezone.Repository

import com.example.gamezone.model.Usuario

class AuthRepository(
    private val usuarioRepo: UsuarioRepository = UsuarioRepository()
) {

    suspend fun login(username: String, password: String): Usuario? {
        return usuarioRepo.validarLogin(username, password)
    }

    suspend fun registrar(usuario: Usuario): Boolean {
        return usuarioRepo.registrarUsuario(usuario)
    }
}

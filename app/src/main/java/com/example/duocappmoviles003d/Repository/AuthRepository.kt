package com.example.duocappmoviles003d.Repository

import com.example.duocappmoviles003d.model.Usuario

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

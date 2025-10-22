package com.example.duocappmoviles003d

data class Usuario(
    var username: String,
    var email: String,
    var password: String
)

val usuariosRegistrados = mutableListOf<Usuario>()
var UsuarioActivo: Usuario? = null

fun registrarUsuario(usuario: Usuario): Boolean {
    if (usuariosRegistrados.any { it.username == usuario.username }) {
        return false
    }
    usuariosRegistrados.add(usuario)
    return true
}

fun validarLogin(username: String, password: String): Boolean {
    return usuariosRegistrados.any { it.username == username && it.password == password }
}

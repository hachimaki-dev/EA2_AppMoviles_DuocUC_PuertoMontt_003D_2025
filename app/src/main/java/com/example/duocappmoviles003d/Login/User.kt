package com.example.duocappmoviles003d.Login

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id_user: Long,
    val email: String,
    val username: String,
    val password: String
)
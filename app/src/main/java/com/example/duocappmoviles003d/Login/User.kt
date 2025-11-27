package com.example.duocappmoviles003d.Login

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id_user") val id: Long? = null,
    val email: String,
    val username: String,
    val password: String
)
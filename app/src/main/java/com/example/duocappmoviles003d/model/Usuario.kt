package com.example.duocappmoviles003d.model

import com.google.gson.annotations.SerializedName

data class Usuario(
    @SerializedName("id")
    val id: Long? = null,
    @SerializedName("username")
    val username: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("password")
    val password: String
)

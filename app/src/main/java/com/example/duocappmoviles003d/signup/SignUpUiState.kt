package com.example.duocappmoviles003d.signup

data class SignUpUiState(
    val name : String = "",
    val email : String = "",
    val password : String = "",
    val errorMessage: String? = ""
)
package com.example.duocappmoviles003d.model

import com.google.gson.annotations.SerializedName

data class EmailRequest(
    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("track")
    val track: String,

    @SerializedName("solicitante")
    val solicitante: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("integrantes")
    val integrantes: String
)
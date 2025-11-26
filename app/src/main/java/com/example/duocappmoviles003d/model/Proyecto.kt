package com.example.duocappmoviles003d.model

import com.google.gson.annotations.SerializedName

data class TrackObj(
    @SerializedName("nombre")
    val nombre: String
)

data class Proyecto(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("nombre")
    val nombre: String,

    @SerializedName("descripcion")
    val descripcion: String,

    @SerializedName("profesor")
    val profesor: String,

    @SerializedName("progress")
    val progress: Int,

    @SerializedName("tracks")
    val trackDetails: TrackObj?
)

data class UserProjectResponse(
    @SerializedName("proyectos")
    val proyecto: Proyecto
)
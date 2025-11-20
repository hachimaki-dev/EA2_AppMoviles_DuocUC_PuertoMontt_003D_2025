package com.example.duocappmoviles003d.model

import com.google.gson.annotations.SerializedName

data class Project(
    @SerializedName("id")
    val id: Long = 0,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("track")
    val track: String,

    @SerializedName("members")
    val members: String,

    @SerializedName("progress")
    val progress: Int
)
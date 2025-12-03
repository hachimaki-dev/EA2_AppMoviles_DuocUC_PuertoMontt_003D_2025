package com.example.tasknoteapp.data

import com.google.gson.annotations.SerializedName
import java.util.UUID

data class Task(
    // Usamos SerializedName para asegurar que coincida con la columna de Supabase
    // id puede ser nulo al enviar una nueva tarea (Supabase lo genera)
    @SerializedName("id") val id: String? = null, 
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("date") val date: String,
    @SerializedName("time") val time: String,
    @SerializedName("priority") val priority: String = "Normal"
)

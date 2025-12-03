package com.example.tasknoteapp.data.remote

import com.example.tasknoteapp.data.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface SupabaseApi {

    @GET("tasks?select=*") 
    suspend fun getTasks(): Response<List<Task>>

    @POST("tasks")
    suspend fun createTask(@Body task: Task): Response<Void>

    // Nuevo endpoint para eliminar una tarea específica por ID
    @DELETE("tasks")
    suspend fun deleteTask(@Query("id") id: String): Response<Void>
}

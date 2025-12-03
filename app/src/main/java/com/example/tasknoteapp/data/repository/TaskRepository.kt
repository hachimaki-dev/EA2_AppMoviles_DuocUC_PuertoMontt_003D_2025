package com.example.tasknoteapp.data.repository

import android.util.Log
import com.example.tasknoteapp.data.Task
import com.example.tasknoteapp.data.remote.RetrofitClient

class TaskRepository {

    private val api = RetrofitClient.api

    suspend fun getTasks(): List<Task> {
        return try {
            val response = api.getTasks()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                Log.e("TaskRepository", "Error fetching tasks: ${response.errorBody()?.string()}")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e("TaskRepository", "Exception fetching tasks", e)
            emptyList()
        }
    }

    suspend fun addTask(task: Task): Boolean {
        return try {
            val response = api.createTask(task)
            if (response.isSuccessful) {
                true
            } else {
                Log.e("TaskRepository", "Error adding task: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            Log.e("TaskRepository", "Exception adding task", e)
            false
        }
    }

    suspend fun deleteTask(id: String): Boolean {
        return try {
            // En Supabase, para borrar por ID usamos: tasks?id=eq.EL_ID
            val response = api.deleteTask("eq.$id")
            if (response.isSuccessful) {
                true
            } else {
                Log.e("TaskRepository", "Error deleting task: ${response.errorBody()?.string()}")
                false
            }
        } catch (e: Exception) {
            Log.e("TaskRepository", "Exception deleting task", e)
            false
        }
    }
}

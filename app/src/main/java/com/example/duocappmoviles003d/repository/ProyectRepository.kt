package com.example.duocappmoviles003d.repository

import com.example.duocappmoviles003d.model.EmailRequest
import com.example.duocappmoviles003d.model.MiembroResponse
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.model.UserProjectResponse
import com.example.duocappmoviles003d.network.FuncionesRetrofitClient
import com.example.duocappmoviles003d.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProjectRepository {

    // Instancias de las API
    private val api = RetrofitClient.apiService
    private val funcionesApi = FuncionesRetrofitClient.apiService

    // Trae los proyectos del usuario (get)
    suspend fun getProyectosPorUsuario(email: String): Result<List<Proyecto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getMyProjects("eq.$email")
                if (response.isSuccessful && response.body() != null) {
                    // Mapear la respuesta para entregar la lista limpia al ViewModel
                    val listaLimpia = response.body()!!.map { it.proyecto }
                    Result.success(listaLimpia)
                } else {
                    Result.failure(Exception("Error al cargar proyectos: ${response.code()}"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Trae los integrantes del proyecto (get)
    suspend fun getIntegrantesProyecto(projectId: Long): Result<List<com.example.duocappmoviles003d.model.Usuario>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getProjectMembers("eq.$projectId")
                if (response.isSuccessful && response.body() != null) {
                    val listaUsuarios = response.body()!!.map { it.usuario }
                    Result.success(listaUsuarios)
                } else {
                    Result.failure(Exception("Error al cargar integrantes"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Trae todos los proyectos (get)
    suspend fun getAllProyectos(): Result<List<Proyecto>> {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getAllProjects()
                if (response.isSuccessful && response.body() != null) {
                    Result.success(response.body()!!)
                } else {
                    Result.failure(Exception("Error al cargar todos los proyectos"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    // Envia un correo con la solicitud de un proyecto (post)
    suspend fun enviarCorreo(request: EmailRequest): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val response = funcionesApi.sendEmail(request)
                if (response.isSuccessful) {
                    Result.success(Unit)
                } else {
                    Result.failure(Exception("Error al enviar correo"))
                }
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }
}
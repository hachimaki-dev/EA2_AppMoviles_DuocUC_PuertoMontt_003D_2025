package com.example.duocappmoviles003d.network
import com.example.duocappmoviles003d.model.MiembroResponse
import com.example.duocappmoviles003d.model.Proyecto
import com.example.duocappmoviles003d.model.UserProjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @GET("proyectos?select=*")
    suspend fun getProjects(): Response<List<Proyecto>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("proyectos")
    suspend fun createProject(@Body project: Proyecto): Response<List<Proyecto>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @GET("miembros_proyectos?select=proyectos(*,tracks(nombre))")
    suspend fun getMyProjects(@Query("user_email") email: String = "eq.estudiante@duocuc.cl"): Response<List<UserProjectResponse>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @GET("miembros_proyectos?select=usuarios(nombre,email)")
    suspend fun getProjectMembers(@Query("proyecto_id") projectId: String): Response<List<MiembroResponse>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @GET("proyectos?select=*,tracks(nombre)")
    suspend fun getAllProjects(): Response<List<Proyecto>>
}
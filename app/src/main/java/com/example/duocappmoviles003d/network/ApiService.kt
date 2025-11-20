package com.example.duocappmoviles003d.network
import com.example.duocappmoviles003d.model.Project
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @GET("projects?select=*")
    suspend fun getProjects(): Response<List<Project>>

    @Headers(
        "apikey: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImFnenBua3l0YWhseG9ra3NhbHhvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM1NjczMjgsImV4cCI6MjA3OTE0MzMyOH0.V-S3fdFHVANEq9kBugc4CXATsp7vSs6Bh4ockhFI6J0",
        "Content-Type: application/json",
        "Prefer: return=representation"
    )
    @POST("projects")
    suspend fun createProject(@Body project: Project): Response<List<Project>>
}
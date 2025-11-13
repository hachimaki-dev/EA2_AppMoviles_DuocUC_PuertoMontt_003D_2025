package com.example.duocappmoviles003d.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.Json

private const val SUPABASE_URL = "https://glhdxyzwztnrogpbcvsk.supabase.co"
private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImdsaGR4eXp3enRucm9ncGJjdnNrIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjMwNjE5MjIsImV4cCI6MjA3ODYzNzkyMn0.SU_cGIYTsRbhut_PHBmF5RlZmZ6YBCK9Gft3PaFeQWs"

val supabase: SupabaseClient = createSupabaseClient(
    supabaseUrl = SUPABASE_URL,
    supabaseKey = SUPABASE_KEY
) {

    // 🔥 Forma correcta en 2024/2025
    install(Auth)
    install(Postgrest)
}


package com.example.duocappmoviles003d.data.repository

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.serialization.json.Json

private const val SUPABASE_URL = "https://TU_PROYECTO.supabase.co"
private const val SUPABASE_KEY = "TU_ANON_KEY"

val supabase: SupabaseClient = createSupabaseClient(
    supabaseUrl = SUPABASE_URL,
    supabaseKey = SUPABASE_KEY
) {

    // 🔥 Forma correcta en 2024/2025
    install(Auth)
    install(Postgrest)
}


    package com.example.duocappmoviles003d
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage
// Reemplaza con tus datos reales de Supabase
const val SUPABASE_URL = "https://ijmwyafrknshqujjybcs.supabase.co"
const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImlqbXd5YWZya25zaHF1amp5YmNzIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjM2Mzk4ODcsImV4cCI6MjA3OTIxNTg4N30.RlQfahO07dK_DKl457xFEBYamaAGbXwFv30YcvoJ2Ws"

val supabase = createSupabaseClient(
    supabaseUrl = SUPABASE_URL,
    supabaseKey = SUPABASE_KEY
) {
    // Instalamos el módulo de base de datos
    install(Postgrest)
    install(Storage)
}
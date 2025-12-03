package com.example.ev3.data.auth

import android.content.Context
import android.content.SharedPreferences
import java.security.MessageDigest

class AuthRepository(private val context: Context) {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isAllowedEmail(email: String): Boolean {
        val regex = Regex("^[A-Za-z0-9._%+-]+@(duoc\\.cl|duocuc\\.cl)$", RegexOption.IGNORE_CASE)
        return regex.matches(email)
    }

    fun register(email: String, password: String): Result<Unit> {
        if (!isAllowedEmail(email)) return Result.failure(IllegalArgumentException("Correo no permitido"))
        if (password.length < 6) return Result.failure(IllegalArgumentException("La contraseña debe tener al menos 6 caracteres"))

        val key = userKey(email)
        if (prefs.contains(key)) return Result.failure(IllegalStateException("Usuario ya existe"))

        val hash = sha256(password)
        prefs.edit().putString(key, hash).apply()
        return Result.success(Unit)
    }

    fun login(email: String, password: String): Result<Unit> {
        if (!isAllowedEmail(email)) return Result.failure(IllegalArgumentException("Correo no permitido"))
        val stored = prefs.getString(userKey(email), null) ?: return Result.failure(IllegalArgumentException("Usuario no encontrado"))
        val inputHash = sha256(password)
        return if (stored == inputHash) Result.success(Unit) else Result.failure(IllegalArgumentException("Contraseña incorrecta"))
    }

    private fun userKey(email: String) = "user_${email.lowercase()}"

    private fun sha256(text: String): String {
        val md = MessageDigest.getInstance("SHA-256")
        val bytes = md.digest(text.toByteArray())
        return bytes.joinToString(separator = "") { b -> "%02x".format(b) }
    }
}


package com.example.ferremas.viewmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.example.ferremas.model.UserProfile

class UserViewModel : ViewModel() {
    private val _currentUser = MutableStateFlow<UserProfile?>(null)
    val currentUser: StateFlow<UserProfile?> = _currentUser.asStateFlow()

    fun loginUser(email: String, password: String) {
        val user = UserProfile(
            uid = "user_${System.currentTimeMillis()}",
            name = email.substringBefore("@").replace(".", " "),
            email = email
        )
        _currentUser.value = user
    }

    fun logout() {
        _currentUser.value = null
    }
}
package com.example.strangemessenger

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    private val _isUserAuthenticated = MutableLiveData<Boolean>()
    val isUserAuthenticated: LiveData<Boolean> get() = _isUserAuthenticated

    // Метод для обновления статуса авторизации
    fun updateAuthStatus(isAuthenticated: Boolean) {
        _isUserAuthenticated.value = isAuthenticated
    }
}
package com.example.code2bridge_app.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.code2bridge_app.data.model.User
import com.example.code2bridge_app.data.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val repository = UserRepository()

    var users = mutableStateListOf<User>()
        private set

    fun loadUsers() {
        viewModelScope.launch {
            try {
                users.clear()
                users.addAll(repository.getUsers())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
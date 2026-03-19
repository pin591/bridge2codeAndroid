package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.api.RetrofitClient
import com.example.code2bridge_app.data.api.UserApi
import com.example.code2bridge_app.data.model.User

class UserRepository {

    private val api = RetrofitClient.api.create(UserApi::class.java)

    suspend fun getUsers(): List<User> {
        return api.getUsers()
    }
}
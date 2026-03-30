package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.UserApi
import com.example.code2bridge_app.data.model.User

class UserRepository {

    private val api = RetrofitClient.instance.create(UserApi::class.java)

    suspend fun getUsers(): List<User> {
        return api.getUsers()
    }
}
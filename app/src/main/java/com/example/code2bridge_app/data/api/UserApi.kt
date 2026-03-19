package com.example.code2bridge_app.data.api

import com.example.code2bridge_app.data.model.User
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>

}
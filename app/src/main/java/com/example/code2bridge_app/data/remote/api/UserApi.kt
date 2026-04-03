package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.model.User
import com.example.code2bridge_app.data.remote.dto.UserCreateDto
import com.example.code2bridge_app.data.remote.dto.UserDto
import com.example.code2bridge_app.data.remote.dto.UserUpdateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @GET("users")
    suspend fun getUsers(): List<User>

    @POST("users")
    suspend fun createUser(@Body user: UserCreateDto): Response<UserDto>

    @PUT("users/{id}")
    suspend fun updateUser(@Path("id") id: Int, @Body user: UserUpdateDto): UserDto

    @DELETE("users/{id}")
    suspend fun softDeleteUser(@Path("id") id: Int): Response<Unit>
}
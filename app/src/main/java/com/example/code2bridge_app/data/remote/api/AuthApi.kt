package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.LoginRequestDto
import com.example.code2bridge_app.data.remote.dto.LoginResponseDto
import com.example.code2bridge_app.data.remote.dto.RegisterRequestDto
import com.example.code2bridge_app.data.remote.dto.RegisterResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequestDto): Response<LoginResponseDto>

    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequestDto): Response<RegisterResponseDto>

}
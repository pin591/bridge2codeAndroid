package com.example.code2bridge_app.data.remote.dto

data class LoginRequestDto(
    val username: String,
    val password: String
)

data class LoginResponseDto(
    val success: Boolean,
    val message: String,
    val idUser: Int?,
    val username: String?,
    val isAdmin: Boolean
)

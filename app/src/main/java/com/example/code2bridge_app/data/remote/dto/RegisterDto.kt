package com.example.code2bridge_app.data.remote.dto

data class RegisterRequestDto (
    val username: String,
    val password: String,
    val email: String,
    val isAdmin: Boolean,

    val name: String,
    val surname: String,
    val age: Int
)

data class RegisterResponseDto (
    var success: Boolean,
    var message: String,
    val idUser: Int,
    val idStudent: Int
)
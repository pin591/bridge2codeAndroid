package com.example.code2bridge_app.data.remote.dto

data class UserDto (
    val idUser: Int,
    val username: String,
    val password: String,
    val email: String,
    val isAdmin: Boolean,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class UserCreateDto (
    val username: String,
    val password: String,
    val email: String,
    val isAdmin: Boolean
)

data class UserUpdateDto (
    val username: String,
    val password: String,
    val email: String,
    val isAdmin: Boolean
)
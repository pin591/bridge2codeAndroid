package com.example.code2bridge_app.data.remote.dto

data class StudentDto (
    val idStudent: Int,
    val username: String,
    val name: String,
    val surname: String,
    val age: Int,
    val enableFlag: Char,
    val startDate: String?,
    val endDate: String?,
    val user: UserDto? = null
)

data class StudentCreateDto(
    val name: String,
    val surname: String,
    val age: Int,
    val idUser: Int?
)

data class StudentUpdateRequestDto(
    val name: String,
    val surname: String,
    val age: Int
)

data class StudentResponseDto(
    val success: Boolean,
    val message: String,
    val idStudent: Int,
    val username: String
)
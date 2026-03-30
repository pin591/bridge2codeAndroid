package com.example.code2bridge_app.data.remote.dto

data class StudentDto (
    val int: Long,
    val name: String,
    val surname: String,
    val age: Int,
    val enableFlag: Char,
    val startDate: String?,
    val endDate: String?
)

data class StudentCreateDto(
    val name: String,
    val surname: String,
    val age: Int
)

data class StudentUpdateDto(
    val name: String,
    val surname: String,
    val age: Int
)
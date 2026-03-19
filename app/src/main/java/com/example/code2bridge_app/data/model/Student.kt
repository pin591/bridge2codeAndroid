package com.example.code2bridge_app.data.model

data class Student (
    val idStudent: Int,
    val name: String,
    val surname: String,
    val age: Int,
    val user: User,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
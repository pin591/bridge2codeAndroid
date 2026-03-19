package com.example.code2bridge_app.data.model

data class Tuition (
    val idTuition: Int,
    val student: Student,
    val course: Course,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
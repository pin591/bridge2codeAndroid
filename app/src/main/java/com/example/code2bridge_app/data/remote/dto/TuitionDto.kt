package com.example.code2bridge_app.data.remote.dto

import com.example.code2bridge_app.data.model.Course
import com.example.code2bridge_app.data.model.Student

data class TuitionDto (
    val idTuition: Int,
    val student: StudentDto,
    val course: CourseDto,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class TuitionCreateDto (
    val student: Student,
    val course: Course,
)

data class TuitionUpdateDto (
    val student: Student,
    val course: Course
)

data class TuitionResponse (
    val success: Boolean,
    val message: String,
    val idTuition: Int
)
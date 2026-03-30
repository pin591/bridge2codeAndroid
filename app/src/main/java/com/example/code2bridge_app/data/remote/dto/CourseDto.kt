package com.example.code2bridge_app.data.remote.dto

import com.example.code2bridge_app.data.model.LookupLine

data class CourseDto (
    val idCourse: Long,
    val staticTitle: String,
    val lookupTitle: LookupLine,
    val description: String,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class CourseCreateDto (
    val staticTitle: String,
    val lookupTitle: LookupLine,
    val description: String
)

data class CourseUpdateDto (
    val staticTitle: String,
    val lookupTitle: LookupLine,
    val description: String
)
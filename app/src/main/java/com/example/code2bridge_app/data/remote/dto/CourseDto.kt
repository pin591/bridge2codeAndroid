package com.example.code2bridge_app.data.remote.dto

data class CourseDto(
    val idCourse: Int,
    val lookupTitle: LookupLineDto?,
    val description: String,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class CourseCreateDto(
    val lookupTitleId: Int,
    val description: String
)

data class CourseUpdateDto(
    val lookupTitleId: Int,
    val description: String
)

data class CourseResponseDto(
    val success: Boolean,
    val message: String,
    val idCourse: Int?
)
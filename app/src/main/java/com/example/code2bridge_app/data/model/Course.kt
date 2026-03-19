package com.example.code2bridge_app.data.model

data class Course (
    val idCourse: Int,
    val staticTitle: String,
    val lookupTitle: LookupLine,
    val description: String,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
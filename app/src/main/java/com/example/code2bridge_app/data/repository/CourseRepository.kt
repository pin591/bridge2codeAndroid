package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.model.Course
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.CourseApi
import com.example.code2bridge_app.data.remote.dto.CourseCreateDto
import com.example.code2bridge_app.data.remote.dto.CourseDto
import com.example.code2bridge_app.data.remote.dto.CourseUpdateDto
import com.example.code2bridge_app.data.remote.dto.LookupLineDto

class CourseRepository {

    private val api = RetrofitClient.instance.create(CourseApi::class.java)

    suspend fun getAll(): List<Course> {
        return api.getAllCourses().map { it.toModel() }
    }

    suspend fun create(dto: CourseCreateDto) = api.createCourse(dto)

    suspend fun update(id: Long, dto: CourseUpdateDto) = api.updateCourse(id, dto)

    suspend fun softDelete(id: Long) = api.softDeleteCourse(id)
}

// Mapper
private fun CourseDto.toModel(): Course {
    return Course(
        idCourse = idCourse.toInt(),
        lookupTitle = lookupTitle?.toModel() ?: LookupLine(0, "", "", "", null, "Y", null, null), // fallback seguro
        description = description,
        enableFlag = enableFlag,
        startDate = startDate,
        endDate = endDate
    )
}

private fun LookupLineDto?.toModel(): LookupLine? {
    if (this == null) return null
    return LookupLine(
        idLookupLine = idLookupLine,
        lineCode = lineCode,
        lineName = lineName,
        lineDescription = lineDescription,
        lookupHeader = null,
        enableFlag = enableFlag,
        startDate = startDate,
        endDate = endDate
    )
}
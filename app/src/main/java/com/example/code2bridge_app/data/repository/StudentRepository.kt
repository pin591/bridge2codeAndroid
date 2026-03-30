package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.StudentApi
import com.example.code2bridge_app.data.remote.dto.StudentCreateDto
import com.example.code2bridge_app.data.remote.dto.StudentDto

class StudentRepository {
    private val api = RetrofitClient.instance.create(StudentApi::class.java)

    suspend fun getAll(): List<StudentDto> = api.getAllStudents()
    suspend fun create(dto: StudentCreateDto) = api.createStudent(dto)
}
package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.model.Student
import com.example.code2bridge_app.data.model.User
import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.StudentApi
import com.example.code2bridge_app.data.remote.dto.StudentCreateDto
import com.example.code2bridge_app.data.remote.dto.StudentDto

class StudentRepository {
    private val api = RetrofitClient.instance.create(StudentApi::class.java)

    suspend fun getAll(): List<Student> {
        return api.getAllStudents().map { it.toModel() }
    }

    suspend fun create(dto: StudentCreateDto) = api.createStudent(dto)

    private fun StudentDto.toModel(): Student {
        return Student(
            idStudent = this.idStudent,
            name = this.name,
            surname = this.surname,
            age = this.age,
            enableFlag = this.enableFlag.toString(), // Manejo de nulos
            startDate = this.startDate,
            endDate = this.endDate,
            // Mapeo manual del usuario si no tienes toModel() en UserDto
            user = User(
            username = this.user?.username ?: "",
            email = this.user?.email ?: "",
            isAdmin = this.user?.isAdmin ?: false
            )
        )
    }
}
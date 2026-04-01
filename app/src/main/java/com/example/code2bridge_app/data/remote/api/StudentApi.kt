package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.StudentCreateDto
import com.example.code2bridge_app.data.remote.dto.StudentDto
import com.example.code2bridge_app.data.remote.dto.StudentResponseDto
import com.example.code2bridge_app.data.remote.dto.StudentUpdateRequestDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface StudentApi {

    @GET("students")
    suspend fun getAllStudents(): List<StudentDto>

    @POST("students")
    suspend fun createStudent(@Body student: StudentCreateDto): Response<StudentDto>

    @PUT("students/{id}")
    suspend fun updateStudent(@Path("id") id: Int, @Body student: StudentUpdateRequestDto): Response<StudentResponseDto>

    @PUT("students/{id}/delete")
    suspend fun softDeleteStudent(@Path("id") id: Int): Response<Unit>
}
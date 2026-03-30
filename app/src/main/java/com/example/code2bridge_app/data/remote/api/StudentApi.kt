package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.StudentCreateDto
import com.example.code2bridge_app.data.remote.dto.StudentDto
import com.example.code2bridge_app.data.remote.dto.StudentUpdateDto
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
    suspend fun createStudent(@Body student: StudentCreateDto): StudentDto

    @PUT("students/{id}")
    suspend fun updateStudent(@Path("id") id: Long, @Body student: StudentUpdateDto): StudentDto

    @DELETE("students/{id}")
    suspend fun softDeleteStudent(@Path("id") id: Long): Response<Unit>

}
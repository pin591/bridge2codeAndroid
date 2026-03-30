package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.CourseCreateDto
import com.example.code2bridge_app.data.remote.dto.CourseDto
import com.example.code2bridge_app.data.remote.dto.CourseUpdateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface CourseApi {

    @GET("courses")
    suspend fun getAllCourses(): List<CourseDto>

    @POST("courses")
    suspend fun createCourse(@Body student: CourseCreateDto): CourseDto

    @PUT("courses/{id}")
    suspend fun updateCourse(@Path("id") id: Long, @Body student: CourseUpdateDto): CourseDto

    @DELETE("courses/{id}")
    suspend fun softDeleteCourse(@Path("id") id: Long): Response<Unit>

}
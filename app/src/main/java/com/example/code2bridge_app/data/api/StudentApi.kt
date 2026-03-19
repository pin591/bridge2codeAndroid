package com.example.code2bridge_app.data.api

import com.example.code2bridge_app.data.model.Student
import retrofit2.http.GET

interface StudentApi {

    @GET("students")
    suspend fun getStudents(): List<Student>

}
package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.TuitionCreateDto
import com.example.code2bridge_app.data.remote.dto.TuitionDto
import com.example.code2bridge_app.data.remote.dto.TuitionUpdateDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface TuitionApi {

    @GET("tuitions")
    suspend fun getAllTuitions(): List<TuitionDto>

    @POST("tuitions")
    suspend fun createTuition(@Body student: TuitionCreateDto): Response<TuitionDto>

    @PUT("tuitions/{id}")
    suspend fun updateTuition(@Path("id") id: Long, @Body student: TuitionUpdateDto): TuitionDto

    @DELETE("tuitions/{id}")
    suspend fun softDeleteTuition(@Path("id") id: Long): Response<Unit>

}
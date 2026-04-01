package com.example.code2bridge_app.data.remote.api

import com.example.code2bridge_app.data.remote.dto.LookupHeaderCreateDto
import com.example.code2bridge_app.data.remote.dto.LookupHeaderDto
import com.example.code2bridge_app.data.remote.dto.LookupLineCreateDto
import com.example.code2bridge_app.data.remote.dto.LookupLineDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LookupApi {
    // Headers
    @GET("api/lookup-headers")
    suspend fun getAllHeaders(): List<LookupHeaderDto>

    @POST("api/lookup-headers")
    suspend fun createHeader(@Body dto: LookupHeaderCreateDto): LookupHeaderDto

    @PUT("api/lookup-headers/{id}/delete")
    suspend fun softDeleteHeader(@Path("id") id: Int): Response<Unit>

    // Lines
    @GET("api/lookup-lines")
    suspend fun getAllLines(): List<LookupLineDto>

    @POST("api/lookup-lines")
    suspend fun createLine(@Body dto: LookupLineCreateDto): LookupLineDto

    @PUT("api/lookup-lines/{id}/delete")
    suspend fun softDeleteLine(@Path("id") id: Int): Response<Unit>
}
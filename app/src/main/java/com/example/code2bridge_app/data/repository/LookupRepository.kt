package com.example.code2bridge_app.data.repository

import com.example.code2bridge_app.data.model.LookupHeader
import com.example.code2bridge_app.data.model.LookupLine
import com.example.code2bridge_app.data.remote.RetrofitClient
import com.example.code2bridge_app.data.remote.api.LookupApi
import com.example.code2bridge_app.data.remote.dto.LookupHeaderCreateDto
import com.example.code2bridge_app.data.remote.dto.LookupHeaderDto
import com.example.code2bridge_app.data.remote.dto.LookupLineCreateDto
import com.example.code2bridge_app.data.remote.dto.LookupLineDto

class LookupRepository {

    private val api = RetrofitClient.instance.create(LookupApi::class.java)

    // Headers
    suspend fun getAllHeaders(): List<LookupHeader> {
        return api.getAllHeaders().map { it.toModel() }
    }

    suspend fun createHeader(dto: LookupHeaderCreateDto) = api.createHeader(dto)

    // Lines
    suspend fun getAllLines(): List<LookupLine> {
        return api.getAllLines().map { it.toModel() }
    }

    suspend fun createLine(dto: LookupLineCreateDto) = api.createLine(dto)

    // Soft Delete
    suspend fun softDeleteHeader(id: Int) = api.softDeleteHeader(id)

    suspend fun softDeleteLine(id: Int) = api.softDeleteLine(id)
}

private fun LookupHeaderDto.toModel(): LookupHeader {
    return LookupHeader(
        idLookupHeader = idLookupHeader,
        headerCode = headerCode,
        headerName = headerName,
        headerDescription = headerDescription,
        enableFlag = enableFlag,
        startDate = startDate,
        endDate = endDate
    )
}

private fun LookupLineDto.toModel(): LookupLine {
    return LookupLine(
        idLookupLine = idLookupLine,
        lineCode = lineCode,
        lineName = lineName,
        lineDescription = lineDescription,
        lookupHeader = lookupHeader?.toModel(),
        enableFlag = enableFlag,
        startDate = startDate,
        endDate = endDate
    )
}
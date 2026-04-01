package com.example.code2bridge_app.data.remote.dto

data class LookupHeaderDto(
    val idLookupHeader: Int,
    val headerCode: String,
    val headerName: String,
    val headerDescription: String,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class LookupHeaderCreateDto(
    val headerCode: String,
    val headerName: String,
    val headerDescription: String
)
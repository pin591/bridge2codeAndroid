package com.example.code2bridge_app.data.remote.dto

data class LookupLineDto(
    val idLookupLine: Int,
    val lineCode: String,
    val lineName: String,
    val lineDescription: String,
    val lookupHeader: LookupHeaderDto?,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)

data class LookupLineCreateDto(
    val lineCode: String,
    val lineName: String,
    val lineDescription: String,
    val lookupHeaderId: Int
)
package com.example.code2bridge_app.data.model

data class LookupHeader (
    val idLookupHeader: Int,
    val headerCode: String,
    val headerName: String,
    val headerDescription: String,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
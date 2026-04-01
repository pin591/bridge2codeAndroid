package com.example.code2bridge_app.data.model

import com.example.code2bridge_app.data.remote.dto.LookupHeaderDto

data class LookupLine (
    val idLookupLine: Int,
    val lineCode: String,
    val lineName: String,
    val lineDescription: String,
    val lookupHeader: LookupHeader?,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
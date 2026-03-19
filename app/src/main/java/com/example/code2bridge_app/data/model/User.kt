package com.example.code2bridge_app.data.model

data class User (
    val idUser: Int,
    val username: String,
    val email: String,
    val isAdmin: Boolean,
    val enableFlag: String,
    val startDate: String?,
    val endDate: String?
)
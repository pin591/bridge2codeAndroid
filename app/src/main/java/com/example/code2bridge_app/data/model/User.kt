package com.example.code2bridge_app.data.model

data class User (
    val idUser: Int = 0,
    val username: String = "",
    val email: String = "",
    val isAdmin: Boolean = false,
    val enableFlag: String = "Y",
    val startDate: String? = null,
    val endDate: String? = null
)
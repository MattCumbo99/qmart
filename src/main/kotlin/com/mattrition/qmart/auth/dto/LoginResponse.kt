package com.mattrition.qmart.auth.dto

data class LoginResponse(
    val token: String,
    val username: String,
)

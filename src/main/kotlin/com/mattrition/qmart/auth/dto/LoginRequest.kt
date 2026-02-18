package com.mattrition.qmart.auth.dto

data class LoginRequest(
    val username: String,
    val rawPassword: String,
)

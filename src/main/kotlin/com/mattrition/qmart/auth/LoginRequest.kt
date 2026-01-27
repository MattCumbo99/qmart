package com.mattrition.qmart.auth

data class LoginRequest(
    val username: String,
    val rawPassword: String
)

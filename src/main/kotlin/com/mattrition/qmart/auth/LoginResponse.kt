package com.mattrition.qmart.auth

data class LoginResponse(
    val token: String,
    val username: String,
)

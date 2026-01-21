package com.mattrition.qmart.auth

import com.mattrition.qmart.user.UserService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {
    @PostMapping("/check-password")
    fun checkPassword(@RequestBody req: PasswordCheckRequest): Boolean {
        return userService.passwordMatches(req.username, req.password)
    }
}
package com.mattrition.qmart.user

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    private val service: UserService
) {
    @GetMapping
    fun getUsers(): List<User> = service.getAllUsers()

    @GetMapping("/{username}")
    fun getUser(@PathVariable username: String): User? = service.getUserByUsername(username)

    @PostMapping
    fun createUser(@RequestBody user: RegisterUser): User {
        return service.createUser(user)
    }
}
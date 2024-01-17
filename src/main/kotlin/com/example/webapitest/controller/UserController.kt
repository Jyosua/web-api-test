package com.example.webapitest.controller

import com.example.webapitest.model.User
import com.example.webapitest.model.dto.CreateUserRequest
import com.example.webapitest.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun listUsers(@RequestParam(defaultValue = "0") page: Int,
                  @RequestParam(defaultValue = "10") size: Int): List<User> {
        return userService.getUsers(page, size)
    }
    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @PostMapping
    fun createUser(@RequestBody createUserRequest: CreateUserRequest): ResponseEntity<User> {
        val user = userService.addUser(createUserRequest)
        return ResponseEntity(user, HttpStatus.CREATED)
    }
}
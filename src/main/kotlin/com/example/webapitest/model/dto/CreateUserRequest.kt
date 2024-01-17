package com.example.webapitest.model.dto

data class CreateUserRequest(
        val organizationId: Long,
        val name: String,
        val email: String,
        val password: String
)
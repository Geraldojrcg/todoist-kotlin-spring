package com.example.todo.todoist.dtos.response

import com.example.todo.todoist.models.User

data class LoginResponseDTO(
    val user: User,
    val token: String,
)

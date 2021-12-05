package com.example.todo.todoist.dtos.request

import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class SignupRequestDTO(
    @field:Email
    val email: String,
    @field:NotBlank
    val name: String,
    @field:NotBlank
    @field:Min(8)
    val password: String,
)

package com.example.todo.todoist.dtos.request

import javax.validation.constraints.Email
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank

data class LoginRequestDTO(
    @field:Email
    val email: String = "",
    @field:NotBlank
    @field:Min(8)
    val password: String = ""
)
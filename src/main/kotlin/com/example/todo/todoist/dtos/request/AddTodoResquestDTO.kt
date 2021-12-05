package com.example.todo.todoist.dtos.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Positive

data class AddTodoResquestDTO(
    @field:NotBlank
    val title: String,
    @field:Positive
    val authorId: Int
)

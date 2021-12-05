package com.example.todo.todoist.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

class NotFoundException(entityName: String):
    ResponseStatusException(HttpStatus.NOT_FOUND, "$entityName not found!") {
}
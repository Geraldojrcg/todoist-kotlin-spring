package com.example.todo.todoist.repositories

import com.example.todo.todoist.models.Todo
import org.springframework.data.repository.CrudRepository

interface TodoRepository: CrudRepository<Todo, Long> {
    fun findAllByOrderByAddedAtDesc(): Iterable<Todo>
    fun findByAuthorId(id: Long): Todo
}
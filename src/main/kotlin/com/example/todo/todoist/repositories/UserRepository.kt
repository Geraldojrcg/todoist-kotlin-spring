package com.example.todo.todoist.repositories

import com.example.todo.todoist.models.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByEmail(email: String?): User?
}
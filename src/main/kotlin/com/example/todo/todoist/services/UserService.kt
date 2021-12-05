package com.example.todo.todoist.services

import com.example.todo.todoist.models.User
import com.example.todo.todoist.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.security.core.context.SecurityContextHolder

@Service
class UserService(
    @Autowired
    private var userRepository: UserRepository,
    @Autowired
    private var bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun findById(id: Long) = userRepository.findById(id)

    fun create(user: User): User {
        user.password = bCryptPasswordEncoder.encode(user.password)
        return userRepository.save(user)
    }

    fun me(): User? = userRepository.findByEmail(getCurrentUserEmail())

    fun getUserByEmail(email: String): User? = userRepository.findByEmail(email)

    private fun getCurrentUserEmail(): String? {
        val user = SecurityContextHolder.getContext().authentication.principal as UserDetailsImpl
        return user.username
    }
}
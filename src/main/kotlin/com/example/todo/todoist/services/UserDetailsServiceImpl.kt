package com.example.todo.todoist.services

import com.example.todo.todoist.repositories.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl: UserDetailsService {
    @Autowired
    private lateinit var userRepository: UserRepository

    override fun loadUserByUsername(email: String?): UserDetails {
        val user = userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return UserDetailsImpl(user)
    }
}
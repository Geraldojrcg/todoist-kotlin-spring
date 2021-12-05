package com.example.todo.todoist.security

import com.example.todo.todoist.dtos.request.LoginRequestDTO
import com.example.todo.todoist.services.UserDetailsImpl
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthenticationFilter(authenticationManager: AuthenticationManager, private var jwtUtil: JwtUtil) :
    UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (email, password) = ObjectMapper().readValue(request.inputStream, LoginRequestDTO::class.java)
            val token = UsernamePasswordAuthenticationToken(email, password)
            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw UsernameNotFoundException("")
        }
    }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = jwtUtil.generateToken(username)
        response.addHeader("Authorization", "Bearer $token")
    }
}
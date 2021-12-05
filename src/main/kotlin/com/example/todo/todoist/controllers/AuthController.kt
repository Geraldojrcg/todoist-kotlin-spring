package com.example.todo.todoist.controllers

import com.example.todo.todoist.dtos.request.LoginRequestDTO
import com.example.todo.todoist.dtos.request.SignupRequestDTO
import com.example.todo.todoist.dtos.response.LoginResponseDTO
import com.example.todo.todoist.models.User
import com.example.todo.todoist.security.JwtUtil
import com.example.todo.todoist.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthController(
    @Autowired
    private var userService: UserService,
    @Autowired
    private var jwtUtil: JwtUtil
) {
    @PostMapping("/login")
    fun login(@RequestBody @Valid loginRequestDTO: LoginRequestDTO): ResponseEntity<LoginResponseDTO> {
        val user = userService.getUserByEmail(loginRequestDTO.email)
            ?: return ResponseEntity.badRequest().build()
        val token = jwtUtil.generateToken(user.email)
        return ResponseEntity.ok().body(LoginResponseDTO(user, token))
    }

    @PostMapping("/signup")
    fun signup(@RequestBody @Valid signupRequestDTO: SignupRequestDTO): ResponseEntity<User> {
        val userCreated = userService.create(User(
            signupRequestDTO.email,
            signupRequestDTO.name,
            signupRequestDTO.password))
        return ResponseEntity.created(URI("")).body(userCreated)
    }
}
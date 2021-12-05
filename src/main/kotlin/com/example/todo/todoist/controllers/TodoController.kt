package com.example.todo.todoist.controllers

import com.example.todo.todoist.dtos.request.AddTodoResquestDTO
import com.example.todo.todoist.exceptions.NotFoundException
import com.example.todo.todoist.models.Todo
import com.example.todo.todoist.services.TodoService
import com.example.todo.todoist.services.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.validation.Valid

@RestController
@RequestMapping("/todos")
class TodoController(
    @Autowired val todoService: TodoService,
    @Autowired val userService: UserService)
{
    @GetMapping
    fun findAll() = ResponseEntity.ok(todoService.findAll())

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long) {
        val todo = todoService.findById(id).orElseThrow { NotFoundException("Todo") }
        ResponseEntity.ok(todo)
    }

    @GetMapping("/from-author/{authorId}")
    fun findByAuthorId(@PathVariable authorId: Long) = ResponseEntity.ok(todoService.findByAuthorId(authorId))

    @PostMapping
    fun create(@RequestBody @Valid dto: AddTodoResquestDTO): ResponseEntity<Todo> {
        val user = userService.findById(dto.authorId.toLong()).orElseThrow { NotFoundException("User") }
        val todo = Todo(dto.title, false, user)
        return ResponseEntity.created(URI("")).body(todoService.create(todo))
    }

    @PatchMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updates: Map<String, Any>): ResponseEntity<Todo> {
        val todo = todoService.update(id, updates)
        return ResponseEntity.ok().body(todo)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) = ResponseEntity.ok(todoService.delete(id))
}
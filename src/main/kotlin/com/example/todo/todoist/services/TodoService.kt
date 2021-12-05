package com.example.todo.todoist.services

import com.example.todo.todoist.dtos.request.AddTodoResquestDTO
import com.example.todo.todoist.exceptions.NotFoundException
import com.example.todo.todoist.models.Todo
import com.example.todo.todoist.repositories.TodoRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoService(
    @Autowired val todoRepository: TodoRepository,
    @Autowired val objectMapper: ObjectMapper
) {
    fun findAll(): Iterable<Todo> = todoRepository.findAllByOrderByAddedAtDesc()

    fun findById(id: Long) = todoRepository.findById(id)

    fun findByAuthorId(id: Long) = todoRepository.findByAuthorId(id)

    fun create(todo: Todo): Todo {

        return todoRepository.save(todo)
    }

    fun update(id: Long, updates: Map<String, Any>): Todo {
        val todo = findById(id)
        val updated = objectMapper.updateValue(todo, updates)
        return todoRepository.save(updated.get())
    }

    fun delete(id: Long) = todoRepository.deleteById(id)
}
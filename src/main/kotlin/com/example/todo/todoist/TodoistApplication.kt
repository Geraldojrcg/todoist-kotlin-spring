package com.example.todo.todoist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TodoistApplication

fun main(args: Array<String>) {
	runApplication<TodoistApplication>(*args)
}

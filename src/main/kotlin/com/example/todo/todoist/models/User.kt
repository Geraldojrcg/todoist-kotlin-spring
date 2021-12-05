package com.example.todo.todoist.models

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class User(
    var email: String,
    var name: String,
    var password: String,
    @Id @GeneratedValue var id: Long? = null)
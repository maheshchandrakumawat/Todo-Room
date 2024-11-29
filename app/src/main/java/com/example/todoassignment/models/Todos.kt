package com.example.todoassignment.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Todos(
    @PrimaryKey
    val id: Int,
    val title: String,
    val description: String,
)
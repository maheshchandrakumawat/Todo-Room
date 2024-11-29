package com.example.todoassignment.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.todoassignment.dao.TodosDao
import com.example.todoassignment.models.Todos

@Database(entities = [Todos::class], version = 1)
abstract class TodosDatabase : RoomDatabase() {
    abstract fun todoDao(): TodosDao
}
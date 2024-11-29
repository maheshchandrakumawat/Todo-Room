package com.example.todoassignment.todorepository

import com.example.todoassignment.dao.TodosDao
import com.example.todoassignment.models.Todos
import javax.inject.Inject

class TodosRepository @Inject constructor(
    private val todosDao: TodosDao
) {
    fun allTodos() = todosDao.selectAll()
    suspend fun addTodo(todos: Todos) = todosDao.insert(todos)
    suspend fun removeTodo(todos: Todos) = todosDao.delete(todos)
}
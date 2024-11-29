package com.example.todoassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todoassignment.models.Todos
import com.example.todoassignment.todorepository.TodosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodosViewModel @Inject constructor(private val repository: TodosRepository) : ViewModel() {

    val todosStateFlow = MutableStateFlow(emptyList<Todos>())
    val todos = todosStateFlow.asStateFlow()

    fun addTodo(todo: Todos) {
        viewModelScope.launch {
            repository.addTodo(todo)
        }
    }

    fun removeTodo(todo: Todos) {
        viewModelScope.launch {
            repository.removeTodo(todo)
        }
    }

    fun allTodos() {
        viewModelScope.launch {
            repository.allTodos().flowOn(Dispatchers.IO).collect { todos ->
                todosStateFlow.update {
                    todos
                }
            }
        }
    }
}
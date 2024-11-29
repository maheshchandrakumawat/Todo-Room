package com.example.todoassignment.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todoassignment.AppBar
import com.example.todoassignment.FloatingActionBtn
import com.example.todoassignment.TodoList
import com.example.todoassignment.models.Todos
import com.example.todoassignment.viewmodels.TodosViewModel
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewTodoScreen(name: String?) {
    val todoViewModel: TodosViewModel = hiltViewModel()
    var titleVal by remember { mutableStateOf("") }
    var descVal by remember { mutableStateOf("") }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Create New ToDo")
                },
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(value = titleVal, onValueChange = {titleVal = it}, label = { Text("Title") },)
                TextField(value = descVal, onValueChange = {descVal = it}, label = { Text("Description") },)
                Button(onClick = {
                    val randomId = (1..1000).random()
                    todoViewModel.addTodo(Todos(id = randomId, title = titleVal, description = descVal))
                }, ) {
                    Text("Save")
                }
            }
        }
    )
}


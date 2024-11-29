package com.example.todoassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.todoassignment.navigation.Navigation
import com.example.todoassignment.navigation.Screen
import com.example.todoassignment.ui.theme.TodoAssignmentTheme
import com.example.todoassignment.viewmodels.TodosViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoAssignmentTheme {
                Navigation()
            }
        }

    }
}

@Composable
fun MainScreen(navController: NavController) {
    AppScaffold(navController)
}

@Composable
fun AppScaffold(navController: NavController) {
    Scaffold(
        topBar = {
            AppBar()
        },
        floatingActionButton = {
            FloatingActionBtn(navController)
        },
        modifier = Modifier.fillMaxSize(),
        content = { padding ->
            TodoList(padding)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        title = {
            Text(text = "Todo Application")
        },
    )
}

@Composable
fun TodoList(paddingValues: PaddingValues) {
    val todoViewModel: TodosViewModel = hiltViewModel()
    todoViewModel.allTodos()
    val todos = todoViewModel.todos.collectAsStateWithLifecycle(lifecycleOwner = LocalLifecycleOwner.current).value
    Log.d("Mahesh", todos.size.toString())
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
    ) {
        LazyColumn {
            items(todos) {
                Row(
                    modifier = Modifier
                        .padding(all = 10.dp)
                        .background(shape = RectangleShape, color = Color.White)
                        .fillMaxSize()
                ) {
                    Column {
                        Text(text = it.title, modifier = Modifier.padding(top = 10.dp, start = 10.dp, end = 10.dp))
                        Text(text = it.description, modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun FloatingActionBtn(navController: NavController) {
    val todoViewModel: TodosViewModel = hiltViewModel()
    FloatingActionButton(onClick = {
        navController.navigate(Screen.DetailScreen.withArgs())
    }) {
        Icon(Icons.Filled.Add, "")
    }
}

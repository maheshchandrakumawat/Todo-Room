package com.example.todoassignment.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todoassignment.MainScreen
import com.example.todoassignment.screens.NewTodoScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(
            route = Screen.DetailScreen.route,
            arguments = listOf(
                navArgument("name") {
                    type = NavType.StringType
                    defaultValue = "Some Default"
                    nullable = true
                }
            )
        ) { entry ->
            NewTodoScreen(name = entry.arguments?.getString("name"))
        }
    }
}
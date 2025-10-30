package com.example.tasknoteapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tasknoteapp.ui.screens.TaskDetailScreen
import com.example.tasknoteapp.ui.screens.TaskFormScreen
import com.example.tasknoteapp.ui.screens.TaskListScreen
import com.example.tasknoteapp.ui.theme.TaskNoteAppTheme
import com.example.tasknoteapp.ui.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskNoteAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val navController = rememberNavController()
                    val taskViewModel: TaskViewModel = viewModel()

                    NavHost(
                        navController = navController,
                        startDestination = "taskList",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("taskList") {
                            TaskListScreen(
                                viewModel = taskViewModel,
                                onAddTask = { navController.navigate("taskForm") },
                                onTaskClick = { taskId ->
                                    navController.navigate("taskDetail/$taskId")
                                }
                            )
                        }
                        composable("taskForm") {
                            TaskFormScreen(
                                viewModel = taskViewModel,
                                onSaveTask = {
                                    if (taskViewModel.saveTask()) {
                                        navController.popBackStack()
                                    }
                                },
                                onBack = { navController.popBackStack() }
                            )
                        }
                        composable(
                            route = "taskDetail/{taskId}",
                            arguments = listOf(navArgument("taskId") { type = NavType.StringType })
                        ) { backStackEntry ->
                            val taskId = backStackEntry.arguments?.getString("taskId")
                            val task = taskViewModel.tasks.find { it.id.toString() == taskId }
                            TaskDetailScreen(
                                task = task,
                                onBack = { navController.popBackStack() }
                            )
                        }
                    }
                }
            }
        }
    }
}

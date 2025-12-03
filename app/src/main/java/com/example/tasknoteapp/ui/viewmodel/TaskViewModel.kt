package com.example.tasknoteapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tasknoteapp.data.Task
import com.example.tasknoteapp.data.repository.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel : ViewModel() {
    
    private val repository = TaskRepository()

    // Task List State
    var tasks by mutableStateOf<List<Task>>(emptyList())
        private set
    
    var isLoading by mutableStateOf(false)
        private set

    // Form State
    var title by mutableStateOf("")
    var description by mutableStateOf("")
    var date by mutableStateOf("")
    var time by mutableStateOf("")
    var priority by mutableStateOf("Normal")

    // Validation State
    var isTitleError by mutableStateOf(false)
    var isDateError by mutableStateOf(false)
    var isTimeError by mutableStateOf(false)

    init {
        loadTasks()
    }

    fun loadTasks() {
        viewModelScope.launch {
            isLoading = true
            val fetchedTasks = repository.getTasks()
            tasks = sortTasks(fetchedTasks)
            isLoading = false
        }
    }
    
    fun deleteTask(taskId: String, onSuccess: () -> Unit) {
        viewModelScope.launch {
            isLoading = true
            val success = repository.deleteTask(taskId)
            if (success) {
                loadTasks()
                onSuccess()
            }
            isLoading = false
        }
    }

    fun onTitleChange(newTitle: String) {
        title = newTitle
        isTitleError = false
    }

    fun onDescriptionChange(newDescription: String) {
        description = newDescription
    }

    fun onDateChange(newDate: String) {
        date = newDate
        isDateError = false 
    }

    fun onTimeChange(newTime: String) {
        time = newTime
        isTimeError = false
    }

    fun onPriorityChange(newPriority: String) {
        priority = newPriority
    }

    fun saveTask(onSuccess: () -> Unit) {
        isTitleError = title.isBlank()
        isDateError = date.length < 8
        isTimeError = time.isNotBlank() && time.length < 4

        if (isTitleError || isDateError || isTimeError) {
            return
        }

        val newTask = Task(
            title = title, 
            description = description, 
            date = date, 
            time = time,
            priority = priority
        )
        
        viewModelScope.launch {
            isLoading = true
            val success = repository.addTask(newTask)
            if (success) {
                // Reset form fields
                title = ""
                description = ""
                date = ""
                time = ""
                priority = "Normal"
                
                // Reload list to get the new item with its ID
                loadTasks()
                onSuccess()
            } else {
                // Handle error (could add an error state here)
            }
            isLoading = false
        }
    }
    
    private fun sortTasks(list: List<Task>): List<Task> {
        return list.sortedWith(
            compareBy { p ->
                when (p.priority) {
                    "Alta" -> 0
                    "Normal" -> 1
                    "Baja" -> 2
                    else -> 3
                }
            }
        )
    }
}

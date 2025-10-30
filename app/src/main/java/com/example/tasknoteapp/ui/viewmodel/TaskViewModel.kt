package com.example.tasknoteapp.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.tasknoteapp.data.Task

class TaskViewModel : ViewModel() {
    // Task List State
    var tasks by mutableStateOf<List<Task>>(emptyList())
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

    fun saveTask(): Boolean {
        isTitleError = title.isBlank()
        isDateError = date.length < 8
        isTimeError = time.isNotBlank() && time.length < 4 // Time is optional, but if present, must be 4 digits

        if (isTitleError || isDateError || isTimeError) {
            return false
        }

        val newTask = Task(
            title = title, 
            description = description, 
            date = date, 
            time = time,
            priority = priority
        )
        
        val updatedList = (tasks + newTask).sortedWith(
            compareBy { p ->
                when (p.priority) {
                    "Alta" -> 0
                    "Normal" -> 1
                    "Baja" -> 2
                    else -> 3
                }
            }
        )
        tasks = updatedList

        // Reset form fields
        title = ""
        description = ""
        date = ""
        time = ""
        priority = "Normal"

        return true
    }
}

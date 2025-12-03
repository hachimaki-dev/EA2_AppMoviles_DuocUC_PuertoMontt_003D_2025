package com.example.tasknoteapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tasknoteapp.ui.utils.DateVisualTransformation
import com.example.tasknoteapp.ui.utils.TimeVisualTransformation
import com.example.tasknoteapp.ui.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    viewModel: TaskViewModel,
    onSaveTask: () -> Unit,
    onBack: () -> Unit
) {
    val priorities = listOf("Baja", "Normal", "Alta")

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Nueva Tarea") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = viewModel.title,
                onValueChange = { viewModel.onTitleChange(it) },
                label = { Text("Título") },
                isError = viewModel.isTitleError,
                modifier = Modifier.fillMaxWidth()
            )
            if (viewModel.isTitleError) {
                Text("El título es obligatorio", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.description,
                onValueChange = { viewModel.onDescriptionChange(it) },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.date,
                onValueChange = { if (it.length <= 8) viewModel.onDateChange(it) },
                label = { Text("Fecha (8 dígitos)") },
                isError = viewModel.isDateError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = DateVisualTransformation()
            )
            if (viewModel.isDateError) {
                Text("La fecha es obligatoria y debe tener 8 dígitos.", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = viewModel.time,
                onValueChange = { if (it.length <= 4) viewModel.onTimeChange(it) },
                label = { Text("Hora (4 dígitos)") },
                isError = viewModel.isTimeError,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = TimeVisualTransformation()
            )
            if (viewModel.isTimeError) {
                Text("Si se especifica la hora, debe tener 4 dígitos.", color = Color.Red)
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Priority Selector
            Text("Prioridad")
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                priorities.forEach { priority ->
                    val isSelected = viewModel.priority == priority
                    val color = when(priority) {
                        "Alta" -> Color(0xFFE53935)
                        "Normal" -> Color(0xFFFFB300)
                        else -> Color(0xFF43A047)
                    }
                    Button(
                        onClick = { viewModel.onPriorityChange(priority) },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSelected) color else Color.Gray
                        )
                    ) {
                        Text(priority)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = onSaveTask,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Guardar Tarea")
            }
        }
    }
}

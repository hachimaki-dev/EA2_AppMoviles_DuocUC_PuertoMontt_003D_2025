package com.example.tasknoteapp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tasknoteapp.data.Task
import com.example.tasknoteapp.ui.utils.formatDisplayDate
import com.example.tasknoteapp.ui.utils.formatDisplayTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    task: Task?,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Detalle de Tarea") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            if (task != null) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    PriorityDot(priority = task.priority)
                    Text(
                        text = "Prioridad: ${task.priority}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))

                Text(task.title, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(8.dp))
                Text(task.description, fontSize = 18.sp)
                Spacer(modifier = Modifier.height(16.dp))
                Text("Fecha: ${formatDisplayDate(task.date)}", fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Hora: ${formatDisplayTime(task.time)}", fontSize = 16.sp)
            } else {
                Text("Tarea no encontrada")
            }
        }
    }
}

@Composable
private fun PriorityDot(priority: String) {
    val color = when (priority) {
        "Alta" -> Color(0xFFE53935)
        "Normal" -> Color(0xFFFFB300)
        "Baja" -> Color(0xFF43A047)
        else -> Color.LightGray
    }
    Box(
        modifier = Modifier
            .size(12.dp)
            .clip(CircleShape)
            .background(color)
    )
}

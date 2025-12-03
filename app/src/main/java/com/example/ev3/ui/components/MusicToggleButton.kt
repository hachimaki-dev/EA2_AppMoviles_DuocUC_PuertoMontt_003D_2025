package com.example.ev3.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.ev3.utils.MusicManager

@Composable
fun MusicToggleButton(modifier: Modifier = Modifier) {
    val isMusicEnabled by MusicManager.isMusicEnabled

    Button(
        onClick = { MusicManager.toggle() },
        modifier = modifier
    ) {
        Text(if (isMusicEnabled) "🔊" else "🔇")
    }
}

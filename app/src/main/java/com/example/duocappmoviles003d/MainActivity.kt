package com.example.duocappmoviles003d

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.duocappmoviles003d.ui.theme.DuocAppMoviles003DTheme
import com.example.duocappmoviles003d.ui.theme.FondoCITT

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DuocAppMoviles003DTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = FondoCITT
                ) {
                    AppNavigation()
                }
            }
        }
    }
}
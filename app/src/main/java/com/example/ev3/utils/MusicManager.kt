package com.example.ev3.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.compose.runtime.mutableStateOf

object MusicManager {
    private var mediaPlayer: MediaPlayer? = null
    val isMusicEnabled = mutableStateOf(true)

    fun initialize(context: Context, musicResId: Int) {
        if (mediaPlayer == null) {
            try {
                mediaPlayer = MediaPlayer.create(context, musicResId)
                mediaPlayer?.isLooping = true
                if (isMusicEnabled.value) {
                    mediaPlayer?.start()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggle() {
        isMusicEnabled.value = !isMusicEnabled.value
        if (isMusicEnabled.value) {
            resumeMusic()
        } else {
            pauseMusic()
        }
    }

    private fun pauseMusic() {
        mediaPlayer?.pause()
    }

    private fun resumeMusic() {
        mediaPlayer?.start()
    }

    fun release() {
        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

    fun onPause() {
        if (isMusicEnabled.value) {
            mediaPlayer?.pause()
        }
    }

    fun onResume() {
        if (isMusicEnabled.value) {
            mediaPlayer?.start()
        }
    }
}

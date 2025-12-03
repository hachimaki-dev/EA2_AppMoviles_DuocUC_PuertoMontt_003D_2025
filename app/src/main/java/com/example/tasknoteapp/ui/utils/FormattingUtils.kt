package com.example.tasknoteapp.ui.utils

// For displaying formatted date in Text composables
fun formatDisplayDate(date: String): String {
    if (date.length != 8) return date
    return "${date.substring(0, 2)}/${date.substring(2, 4)}/${date.substring(4, 8)}"
}

// For displaying formatted time in Text composables
fun formatDisplayTime(time: String): String {
    if (time.length != 4) return time
    return "${time.substring(0, 2)}:${time.substring(2, 4)}"
}

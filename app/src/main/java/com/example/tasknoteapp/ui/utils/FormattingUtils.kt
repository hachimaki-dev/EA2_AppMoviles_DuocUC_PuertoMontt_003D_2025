package com.example.tasknoteapp.ui.utils

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

// For EditText visual transformation
class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i % 2 == 1 && i < 4) out += '/'
        }
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                if (offset <= 3) return offset + 1
                return if (offset <= 8) offset + 2 else 10
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                if (offset <= 5) return offset - 1
                return if (offset <= 10) offset - 2 else 8
            }
        }
        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}

// For EditText visual transformation
class TimeVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= 4) text.text.substring(0..3) else text.text
        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == 1) out += ':'
        }
        val offsetMapping = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                if (offset <= 1) return offset
                return if (offset <= 4) offset + 1 else 5
            }

            override fun transformedToOriginal(offset: Int): Int {
                if (offset <= 2) return offset
                return if (offset <= 5) offset - 1 else 4
            }
        }
        return TransformedText(AnnotatedString(out), offsetMapping)
    }
}

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

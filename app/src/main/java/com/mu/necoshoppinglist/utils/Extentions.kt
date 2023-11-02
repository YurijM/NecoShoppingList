package com.mu.necoshoppinglist.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getProgress(
    allItemsCount: Int,
    selectedItemsCount: Int
): Float {
    return if (allItemsCount == 0) {
        0f
    } else {
        selectedItemsCount.toFloat() / allItemsCount.toFloat()
    }
}

fun getProgressColor(progress: Float): Color {
    return when (progress) {
        in 0.0..0.339 -> Color.Red
        in 0.34..0.669 -> Color.Magenta
        in 0.67..1.0 -> Color.Blue
        else -> Color.Red
    }
}

fun getCurrentTime(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return formatter.format(calendar.time)
}

fun stringToColor(color: String) =
    Color(android.graphics.Color.parseColor(color))

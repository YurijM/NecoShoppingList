package com.mu.necoshoppinglist.utils

import androidx.compose.ui.graphics.Color
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun getCurrentTime(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return formatter.format(calendar.time)
}

fun stringToColor(color: String) =
    Color(android.graphics.Color.parseColor(color))

package com.mu.necoshoppinglist.utils

import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

fun ViewModel.getCurrentTime(): String {
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
    val calendar = Calendar.getInstance()

    return formatter.format(calendar.time)
}
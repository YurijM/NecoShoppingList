package com.mu.necoshoppinglist.screens.about

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AboutScreen() {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentWidth()
            .wrapContentHeight(),
        text = "About Screen"
    )
}
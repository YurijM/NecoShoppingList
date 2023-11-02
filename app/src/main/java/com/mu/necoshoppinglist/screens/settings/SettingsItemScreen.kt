package com.mu.necoshoppinglist.screens.settings

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mu.necoshoppinglist.utils.ColorItem

@Composable
fun SettingsItemScreen(item: ColorItem) {
    IconButton(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .clip(CircleShape)
            .size(36.dp),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = Color(
                android.graphics.Color.parseColor(item.color))
        ),
        onClick = {}
    ) {
        if (item.isSelected) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Check",
                tint = Color.White
            )
        }
    }
}
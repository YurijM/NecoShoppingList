package com.mu.necoshoppinglist.screens.add_item_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain

@Composable
fun AddItemScreen(
    item: AddItemEntity,
    onEvent: (AddItemEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp, start = 4.dp, end = 4.dp)
            .clickable {
                onEvent(AddItemEvent.OnShowEditDialog(item))
            },
        colors = CardDefaults.cardColors(
            containerColor = BlueLight,
            contentColor = BlueMain
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f),
                text = item.name
            )
            Checkbox(
                colors = CheckboxDefaults.colors(
                    checkedColor = Color.White,
                    checkmarkColor = BlueMain
                ),
                checked = item.isChecked,
                onCheckedChange = { isChecked ->
                    onEvent(AddItemEvent.OnCheckedChange(item.copy(isChecked = isChecked)))
                }
            )
            IconButton(
                colors = IconButtonDefaults.iconButtonColors(
                    contentColor = Color.Red
                ),
                onClick = {
                    onEvent(AddItemEvent.OnDelete(item))
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}
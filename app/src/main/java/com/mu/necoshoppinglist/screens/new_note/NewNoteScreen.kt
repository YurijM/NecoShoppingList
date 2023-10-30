package com.mu.necoshoppinglist.screens.new_note

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.ui.theme.LightText
import com.mu.necoshoppinglist.utils.UiEvent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewNoteScreen(
    viewModel: NewNoteViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.PopBackStack -> {
                    onPopBackStack()
                }
                else -> {}
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight)
    ) {
        Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = BlueLight
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.title,
                        onValueChange = { text ->
                            viewModel.onEvent(NewNoteEvent.OnTitleChange(text))
                        },
                        label = {
                            Text(
                                text = "Название статьи",
                                fontSize = 16.sp
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            textColor = BlueMain,
                            focusedIndicatorColor = BlueMain,
                            cursorColor = BlueMain,
                            focusedLabelColor = Color.White,
                            unfocusedIndicatorColor = BlueMain,
                            unfocusedLabelColor = LightText
                        )
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = BlueMain
                        ),
                        onClick = {
                            viewModel.onEvent(NewNoteEvent.OnSave)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Send,
                            contentDescription = "Delete"
                        )
                    }
                }
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp),
                    value = viewModel.description,
                    onValueChange = { text ->
                        viewModel.onEvent(NewNoteEvent.OnDescriptionChange(text))
                    },
                    label = {
                        Text(
                            text = "Текст статьи",
                            fontSize = 14.sp
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        textColor = BlueMain,
                        focusedIndicatorColor = BlueMain,
                        cursorColor = BlueMain,
                        focusedLabelColor = Color.White,
                        unfocusedIndicatorColor = BlueMain,
                        unfocusedLabelColor = LightText
                    )
                )
            }
        }
    }
}

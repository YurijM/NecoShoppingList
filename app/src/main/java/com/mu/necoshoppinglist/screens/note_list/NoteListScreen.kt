package com.mu.necoshoppinglist.screens.note_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.MainDialog

@Composable
fun NoteListScreen(
    paddingValues: PaddingValues,
    viewModel: NoteViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val notes = viewModel.notes.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }
                else -> {}
            }
        }
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
        contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())
    ) {
        items(notes.value) { item ->
           NoteItemScreen(item) { event ->
                viewModel.onEvent(event)
            }
        }
    }
    MainDialog(dialogController = viewModel)

    if (notes.value.isEmpty()) {
        Text(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight(),
            text = "Нет ни одной статьи",
            color = BlueMain,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}
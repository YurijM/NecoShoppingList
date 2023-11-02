package com.mu.necoshoppinglist.screens.note_list

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.ui.theme.LightText
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.MainDialog

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteListScreen(
    paddingValues: PaddingValues,
    viewModel: NoteViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                is UiEvent.ShowSnackBar -> {
                    val result = snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = "Отменить"
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteEvent.UnDoneDeleteItem)
                    }
                }

                else -> {}
            }
        }
    }

    Scaffold(
        modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.padding(bottom = 32.dp),
                    snackbarData = data,
                    containerColor = Color.Red,
                    contentColor = Color.White,
                    actionColor = Color.Yellow,
                    shape = RoundedCornerShape(8.dp)
                )
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp, start = 8.dp, end = 8.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextField(
                        modifier = Modifier
                            //.weight(1f)
                            .padding(bottom = 8.dp),
                        value = viewModel.searchText.value,
                        onValueChange = { text ->
                            viewModel.onEvent(NoteEvent.OnSearchTextChange(text))
                        },
                        label = {
                            Text(
                                text = "Поиск...",
                                fontSize = 16.sp
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            textColor = BlueMain,
                            focusedIndicatorColor = BlueMain,
                            cursorColor = BlueMain,
                            focusedLabelColor = LightText,
                            unfocusedIndicatorColor = BlueMain,
                            unfocusedLabelColor = LightText
                        )
                    )
                    IconButton(
                        modifier = Modifier
                            .clip(CircleShape)
                            .size(20.dp),
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White,
                            containerColor = Color.Red
                        ),
                        onClick = {
                            viewModel.onEvent(NoteEvent.OnSearchTextClear)
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = "Clear"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                //contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())
            ) {
                //items(viewModel.notes.value) { item ->
                items(viewModel.notes.value) {item ->
                    NoteItemScreen(viewModel.titleColor.value, item) { event ->
                        viewModel.onEvent(event)
                    }
                }
            }
            MainDialog(dialogController = viewModel)

            if (viewModel.notes.value.isEmpty()) {
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
    }
}

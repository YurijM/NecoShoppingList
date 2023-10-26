package com.mu.necoshoppinglist.screens.add_item_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.ui.theme.LightText
import com.mu.necoshoppinglist.utils.dialog.MainDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun AddItemListScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val list = viewModel.list.collectAsState(initial = emptyList())

    val scope = rememberCoroutineScope()
    val snackbarHostState = remember {
        SnackbarHostState()
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(BlueLight)
                        .padding(start = 8.dp, bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemName.value,
                        onValueChange = { text ->
                            viewModel.onEvent(AddItemEvent.OnTextChange(text))
                        },
                        label = {
                            Text(
                                text = "Name",
                                //fontSize = 16.sp
                            )
                        },
                        singleLine = true,
                        /*textStyle = TextStyle(
                        fontSize = 20.sp
                    ),*/
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            textColor = BlueMain,
                            focusedIndicatorColor = BlueMain,
                            cursorColor = BlueMain,
                            focusedLabelColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent,
                            unfocusedLabelColor = LightText
                        )
                    )
                    IconButton(
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = BlueMain
                        ),
                        onClick = {
                            if (viewModel.itemName.value.isEmpty()) {
                                scope.launch {
                                    snackbarHostState.showSnackbar("Значение поля не может быть пустым")
                                }
                            } else {
                                viewModel.onEvent(AddItemEvent.OnItemSave)
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add"
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(list.value) { item ->
                    AddItemScreen(item) { event ->
                        viewModel.onEvent(event)
                    }
                }
            }
        }
        MainDialog(dialogController = viewModel)

        if (list.value.isEmpty()) {
            Text(
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight(),
                text = "Нет никаких покупок",
                color = BlueMain,
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}
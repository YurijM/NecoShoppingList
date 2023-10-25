package com.mu.necoshoppinglist.screens.add_item_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.utils.dialog.MainDialog

@Composable
fun AddItemListScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val list = viewModel.list?.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
    ) {
        if (list != null) {

            items(5) {
                AddItemScreen(AddItemEntity(
                    id = it,
                    name = "Item $it",
                    isChecked = true,
                    shoppingListId = 1
                )) { event ->
                    viewModel.onEvent(event)
                }
            }
        }
    }
    MainDialog(dialogController = viewModel)
}
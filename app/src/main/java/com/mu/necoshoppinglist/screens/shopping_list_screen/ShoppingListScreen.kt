package com.mu.necoshoppinglist.screens.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.ui.theme.GrayLight

@Composable
fun ShoppingListScreen(
    paddingValues: PaddingValues,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val list = viewModel.list.collectAsState(initial = emptyList<ShoppingListItemEntity>())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
        contentPadding = PaddingValues(bottom = paddingValues.calculateBottomPadding())
    ) {
        items(list.value) { item ->
            ShoppingListItemScreen(item)
        }
    }
}
package com.mu.necoshoppinglist.screens.shopping_list_screen

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
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.MainDialog

@Composable
fun ShoppingListScreen(
    paddingValues: PaddingValues,
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val list = viewModel.list.collectAsState(initial = emptyList<ShoppingListItemEntity>())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
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
        items(list.value) { item ->
            ShoppingListItemScreen(item) { event ->
                viewModel.onEvent(event)
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
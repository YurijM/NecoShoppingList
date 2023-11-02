package com.mu.necoshoppinglist.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain

@Composable
fun SettingsScreen(
    paddingValues: PaddingValues,
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val colors = viewModel.colors.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlueLight)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 8.dp,
                    start = 8.dp,
                    end = 8.dp,
                    bottom = paddingValues.calculateBottomPadding()
                )
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                textAlign = TextAlign.Center,
                text = "Текущий цвет заголовка списка покупок",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = BlueMain
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                text = "Выберите цвет для заголовка:",
                color = BlueMain
            )
            Spacer(modifier = Modifier.height(8.dp))
            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                items(colors) { color ->
                    SettingsItemScreen(color) { event ->
                        viewModel.onEvent(event)
                    }
                }

            }
        }
    }
}
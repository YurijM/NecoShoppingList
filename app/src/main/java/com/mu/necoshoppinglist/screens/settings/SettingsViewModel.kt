package com.mu.necoshoppinglist.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.utils.ColorItem
import com.mu.necoshoppinglist.utils.ColorObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {
    val colors = mutableStateOf<List<ColorItem>>(emptyList())

    init {
        viewModelScope.launch {
            val colorsTemp = ArrayList<ColorItem>()

            ColorObject.listColors.forEach { color ->
                colorsTemp.add(
                    ColorItem(
                        color = color,
                        isSelected = false
                    )
                )
            }

            colors.value = colorsTemp
        }
    }
}
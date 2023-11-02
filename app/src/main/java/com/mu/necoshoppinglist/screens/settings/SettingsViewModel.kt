package com.mu.necoshoppinglist.screens.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data_storage.DataStorageManager
import com.mu.necoshoppinglist.utils.ColorItem
import com.mu.necoshoppinglist.utils.ColorObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val dataStoreManager: DataStorageManager
) : ViewModel() {
    val colors = mutableStateOf<List<ColorItem>>(emptyList())

    val currentColor = mutableStateOf("#03A9F4")

    init {
        viewModelScope.launch {
            dataStoreManager.getStringPreference(
                DataStorageManager.TITLE_COLOR,
                "#03A9F4"
            ).collect { selectedColor ->
                currentColor.value = selectedColor

                val colorsTemp = ArrayList<ColorItem>()

                ColorObject.listColors.forEach { color ->
                    colorsTemp.add(
                        ColorItem(
                            color = color,
                            isSelected = color == selectedColor
                        )
                    )
                }

                colors.value = colorsTemp
            }
        }
    }

    fun onEvent(event: SettingsEvent) {
        when (event) {
            is SettingsEvent.OnItemSelected -> {
                viewModelScope.launch {
                    dataStoreManager.saveStringPreference(
                        key = DataStorageManager.TITLE_COLOR,
                        value = event.color
                    )
                    currentColor.value = event.color
                }
            }
        }
    }
}
package com.mu.necoshoppinglist.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.data.repository.ShoppingListItemRepository
import com.mu.necoshoppinglist.utils.Routes
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: ShoppingListItemRepository
): ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("List name:")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    var showFloatingButton = mutableStateOf(true)
        private set

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItemEntity(
                            null,
                            editableText.value,
                            "01.10.2023 12:00",
                            //getCurrentTime(),
                            0,
                            0
                        )
                    )
                }
            }
            is MainEvent.OnNewItemClick -> {
                if(event.route == Routes.SHOPPING_LIST){
                    openDialog.value = true
                } else {
                    sendUiEvent(UiEvent.NavigateMain(Routes.NEW_NOTE + "/-1"))
                }

            }
            is MainEvent.Navigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))
                showFloatingButton.value = !(event.route == Routes.ABOUT
                        || event.route == Routes.SETTINGS)
            }
            is MainEvent.NavigateAdd -> {
                sendUiEvent(UiEvent.NavigateMain(event.route))
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {
        when(event){
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnOK -> {
                onEvent(MainEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnDoNotClose -> {
                openDialog.value = true
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}
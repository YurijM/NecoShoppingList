package com.mu.necoshoppinglist.screens.shopping_list_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.data.repository.ShoppingListItemRepository
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import com.mu.necoshoppinglist.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListItemRepository
) : ViewModel(), DialogController {
    val list = repository.getAllItems()

    private var item: ShoppingListItemEntity? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when (event) {
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnOK -> {
                openDialog.value = false

                if (showEditableText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        item?.let { repository.deleteItem(it) }
                    }
                }

                editableText.value = ""
            }
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnDoNotClose -> {
                openDialog.value = true
            }
        }
    }

    fun onEvent(event: ShoppingListEvent) {
        when (event) {
            is ShoppingListEvent.OnShowEditDialog -> {
                item = event.item
                openDialog.value = true
                editableText.value = item?.name ?: ""
                showEditableText.value = true

                dialogTitle.value = if (item == null) {
                    "New item"
                } else {
                    "Редактирование"
                }
            }

            is ShoppingListEvent.OnShowDeleteDialog -> {
                item = event.item
                openDialog.value = true
                dialogTitle.value = "Вы действительно хотите удалить покупки \"${item?.name}\"?"
                showEditableText.value = false
            }

            is ShoppingListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }

            is ShoppingListEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) {
                    sendUiEvent(UiEvent.ShowSnackBar("Значение поля не может быть пустым"))
                    onDialogEvent(DialogEvent.OnDoNotClose)
                    return
                }

                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItemEntity(
                            item?.id,
                            editableText.value,
                            item?.time ?: getCurrentTime(),
                            item?.allItemsCount ?: 0,
                            item?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send((event))
        }
    }
}
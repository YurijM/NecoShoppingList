package com.mu.necoshoppinglist.screens.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.data.repository.AddItemRepository
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel(), DialogController {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    var list: Flow<List<AddItemEntity>>
    private var shoppingListItem: ShoppingListItemEntity? = null
    var itemName = mutableStateOf("")
    private var listId: Int

    init {
        listId = saveStateHandle.get<Int>("listId") ?: -1
        list = repository.getAllItemsById(listId)
        viewModelScope.launch {
            shoppingListItem = repository.getShoppingListItemById(listId)
        }
    }

    var item: AddItemEntity = AddItemEntity(
        id = null,
        name = "",
        isChecked = false,
        shoppingListId = listId
    )

    override var dialogTitle = mutableStateOf("Edit")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnOK -> {
                openDialog.value = false
                item = item.copy(name = editableText.value)
                editableText.value = ""
                onEvent(AddItemEvent.OnItemSave)
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

    fun onEvent(event: AddItemEvent) {
        when(event) {
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }

                updateShoppingListItemCount()
            }
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }

                updateShoppingListItemCount()
            }
            is AddItemEvent.OnItemSave -> {
                if (listId == -1) return

                if (item.name.isEmpty()) {
                    sendUiEvent(UiEvent.ShowSnackBar("Значение поля не может быть пустым"))
                    //onDialogEvent(DialogEvent.OnDoNotClose)
                    return
                }

                viewModelScope.launch {
                    repository.insertItem(item)
                }

                itemName.value = ""
                item =  AddItemEntity(
                    id = null,
                    name = "",
                    isChecked = false,
                    shoppingListId = listId
                )

                updateShoppingListItemCount()
            }
            is AddItemEvent.OnTextChange -> {
                itemName.value = event.text
                item = item.copy(name = itemName.value)
            }
            is AddItemEvent.OnShowEditDialog -> {
                openDialog.value = true
                item = event.item
                editableText.value = item.name
            }
        }
    }

    private fun updateShoppingListItemCount() {
        viewModelScope.launch {
            list.collect { list ->
                var counter = 0
                list.forEach { item ->
                    if (item.isChecked) counter++
                }

                shoppingListItem?.let {
                    repository.insertItem(
                        it.copy(
                            allItemsCount = list.size,
                            allSelectedItemsCount = counter
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
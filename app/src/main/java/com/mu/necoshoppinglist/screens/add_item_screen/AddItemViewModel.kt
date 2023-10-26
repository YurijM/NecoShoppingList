package com.mu.necoshoppinglist.screens.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import com.mu.necoshoppinglist.data.repository.AddItemRepository
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel(), DialogController {
    var list: Flow<List<AddItemEntity>>
    private var shoppingListItem: ShoppingListItemEntity? = null
    private var itemName = mutableStateOf("")
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

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
            is DialogEvent.OnOK -> {
                onEvent(AddItemEvent.OnItemSave)
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                itemName.value = editableText.value
                editableText.value = ""
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

                viewModelScope.launch {
                    repository.insertItem(item)
                }

                itemName.value = ""
                AddItemEntity(
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
}
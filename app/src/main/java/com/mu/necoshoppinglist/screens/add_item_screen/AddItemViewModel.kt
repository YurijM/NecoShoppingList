package com.mu.necoshoppinglist.screens.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.repository.AddItemRepository
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: AddItemRepository,
    saveStateHandle: SavedStateHandle
) : ViewModel(), DialogController {
    var list: Flow<List<AddItemEntity>>? = null

    init {
        val listId = saveStateHandle.get<Int>("listId")
        list = listId?.let { repository.getAllItemsById(it) }
    }

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
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
        }
    }

    fun onEvent(event: AddItemEvent) {

    }
}
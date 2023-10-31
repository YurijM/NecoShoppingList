package com.mu.necoshoppinglist.screens.note_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import com.mu.necoshoppinglist.data.repository.NoteItemRepository
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.DialogController
import com.mu.necoshoppinglist.utils.dialog.DialogEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val repository: NoteItemRepository
) : ViewModel(), DialogController {
    val notes = repository.getAllItems()
    private var note: NoteItemEntity? = null

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
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
        when (event) {
            is DialogEvent.OnOK -> {
                viewModelScope.launch {
                    note?.let { repository.deleteItem(it) }
                }
                openDialog.value = false
            }
            is DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            else -> { }
        }
    }

    fun onEvent(event: NoteEvent) {
        when(event) {
            is NoteEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is NoteEvent.OnShowDeleteDialog -> {
                openDialog.value = true
                note = event.item
                dialogTitle.value = "Вы действительно хотите удалить статью \"${note?.title}\""
            }
        }
    }
}
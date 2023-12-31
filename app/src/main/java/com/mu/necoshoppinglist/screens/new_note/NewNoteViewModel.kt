package com.mu.necoshoppinglist.screens.new_note

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import com.mu.necoshoppinglist.data.repository.NoteItemRepository
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.getCurrentTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewNoteViewModel @Inject constructor(
    private val repository: NoteItemRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    private val noteId: Int
    private lateinit var note: NoteItemEntity

    var title by mutableStateOf("")
        private set
    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1
        if (noteId != -1) {
            viewModelScope.launch {
                note = repository.getNoteItemById(noteId)

                title = note.title
                description = note.description
            }
        } else {
            note = NoteItemEntity(
                id = null,
                title = "",
                description = "",
                time = ""
            )
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when(event) {
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
                note = note.copy(title = title)
            }
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
                note = note.copy(description = description)
            }
            is NewNoteEvent.OnSave -> {
                if (title.isEmpty()) {
                    sendUiEvent(UiEvent.ShowSnackBar("Значение поля не может быть пустым"))
                    return
                }

                viewModelScope.launch {
                    repository.insertItem(note.copy(time = if (note.id != null) note.time else getCurrentTime()))

                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }
}
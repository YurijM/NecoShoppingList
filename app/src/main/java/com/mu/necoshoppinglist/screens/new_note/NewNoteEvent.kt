package com.mu.necoshoppinglist.screens.new_note

sealed class NewNoteEvent {
    data class OnTitleChange(val title: String) : NewNoteEvent()
    data class OnDescriptionChange(val description: String) : NewNoteEvent()
    object OnSave : NewNoteEvent()
}

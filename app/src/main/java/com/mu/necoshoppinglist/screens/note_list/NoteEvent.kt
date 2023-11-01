package com.mu.necoshoppinglist.screens.note_list

import com.mu.necoshoppinglist.data.entity.NoteItemEntity

sealed class NoteEvent {
    data class OnShowDeleteDialog(val item: NoteItemEntity) : NoteEvent()
    data class OnItemClick(val route: String) : NoteEvent()
    object UnDoneDeleteItem: NoteEvent()
}

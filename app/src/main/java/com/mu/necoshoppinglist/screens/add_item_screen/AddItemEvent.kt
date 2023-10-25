package com.mu.necoshoppinglist.screens.add_item_screen

import com.mu.necoshoppinglist.data.entity.AddItemEntity

sealed class AddItemEvent {
    data class OnDelete(val item: AddItemEntity) : AddItemEvent()
    data class OnShowEditDialog(val item: AddItemEntity) : AddItemEvent()
    data class OnTextChange(val text: String) : AddItemEvent()
    data class OnCheckedChange(val item: AddItemEntity) : AddItemEvent()
    object OnItemSave: AddItemEvent()
}

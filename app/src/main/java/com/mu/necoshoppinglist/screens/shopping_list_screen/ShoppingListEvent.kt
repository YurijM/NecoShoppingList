package com.mu.necoshoppinglist.screens.shopping_list_screen

import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity

sealed class ShoppingListEvent {
    data class OnShowEditDialog(val item: ShoppingListItemEntity?) : ShoppingListEvent()
    data class OnShowDeleteDialog(val item: ShoppingListItemEntity) : ShoppingListEvent()
    data class OnItemClick(val route: String) : ShoppingListEvent()
    object OnItemSave : ShoppingListEvent()
}
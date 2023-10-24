package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

interface AddItemRepository {
    suspend fun insertItem(item: AddItemEntity)
    suspend fun deleteItem(item: AddItemEntity)
    fun getAllItemsById(shoppingListId: Int): Flow<AddItemEntity>
    suspend fun getShoppingListItemById(shoppingListId: Int): ShoppingListItemEntity
    suspend fun insertItem(item: ShoppingListItemEntity)

}
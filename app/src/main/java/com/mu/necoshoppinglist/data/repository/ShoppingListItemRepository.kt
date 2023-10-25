package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

interface ShoppingListItemRepository {
    suspend fun insertItem(item: ShoppingListItemEntity)
    suspend fun deleteItem(item: ShoppingListItemEntity)
    fun getAllItems(): Flow<List<ShoppingListItemEntity>>
}
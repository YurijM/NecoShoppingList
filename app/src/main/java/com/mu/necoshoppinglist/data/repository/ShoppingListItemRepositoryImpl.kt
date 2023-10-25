package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.dao.ShoppingListItemDao
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

class ShoppingListItemRepositoryImpl(
    private val dao: ShoppingListItemDao
) : ShoppingListItemRepository {
    override suspend fun insertItem(item: ShoppingListItemEntity) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: ShoppingListItemEntity) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<ShoppingListItemEntity>> {
        return dao.getAllItems()
    }
}
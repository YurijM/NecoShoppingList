package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.dao.AddItemDao
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

class AddItemRepositoryImpl(
    private val dao: AddItemDao
) : AddItemRepository {
    override suspend fun insertItem(item: AddItemEntity) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: AddItemEntity) {
        dao.deleteItem(item)
    }

    override fun getAllItemsById(shoppingListId: Int): Flow<List<AddItemEntity>> {
        return dao.getAllItemsById(shoppingListId)
    }

    override suspend fun getShoppingListItemById(shoppingListId: Int): ShoppingListItemEntity {
        return dao.getShoppingListItemById(shoppingListId)
    }

    override suspend fun insertItem(item: ShoppingListItemEntity) {
        dao.insertItem(item)
    }
}
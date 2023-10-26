package com.mu.necoshoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItemEntity)

    @Delete
    suspend fun deleteItem(item: ShoppingListItemEntity)

    @Query("DELETE FROM add_item_table WHERE shoppingListId = :listId")
    suspend fun deleteAddItems(listId: Int)

    @Query("SELECT * FROM shopping_list_item_table")
    fun getAllItems(): Flow<List<ShoppingListItemEntity>>

    @Transaction
    suspend fun deleteShoppingList(item: ShoppingListItemEntity) {
        deleteAddItems(item.id!!)
        deleteItem(item)
    }
}
package com.mu.necoshoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItemEntity)

    @Delete
    suspend fun deleteItem(item: AddItemEntity)

    @Query("SELECT * FROM add_item_table " +
            "WHERE shoppingListId = :shoppingListId")
    fun getAllItemsById(shoppingListId: Int): Flow<AddItemEntity>

    @Query("SELECT * FROM shopping_list_item_table " +
            "WHERE id = :shoppingListId")
    suspend fun getShoppingListItemById(shoppingListId: Int): ShoppingListItemEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItemEntity)
}
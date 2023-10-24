package com.mu.necoshoppinglist.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mu.necoshoppinglist.data.dao.AddItemDao
import com.mu.necoshoppinglist.data.dao.NoteItemDao
import com.mu.necoshoppinglist.data.dao.ShoppingListItemDao
import com.mu.necoshoppinglist.data.entity.AddItemEntity
import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import com.mu.necoshoppinglist.data.entity.ShoppingListItemEntity

@Database(
    entities = [
        AddItemEntity::class,
        NoteItemEntity::class,
        ShoppingListItemEntity::class
    ],
    version = 1
)
abstract class MainDb : RoomDatabase() {
    abstract val shoppingListItemDao: ShoppingListItemDao
    abstract val addItemDao: AddItemDao
    abstract val noteItemDao: NoteItemDao
}
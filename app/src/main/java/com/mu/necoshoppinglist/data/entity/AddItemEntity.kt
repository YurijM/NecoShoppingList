package com.mu.necoshoppinglist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "add_item_table")
data class AddItemEntity(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val isChecked: Boolean,
    val shoppingListId: Int
)

package com.mu.necoshoppinglist.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_item_table")
data class NoteItemEntity(
    @PrimaryKey
    val id: Int? = null,
    val title: String,
    val description: String,
    val time: String
)

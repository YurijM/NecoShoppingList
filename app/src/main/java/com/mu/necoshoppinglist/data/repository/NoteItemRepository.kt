package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import kotlinx.coroutines.flow.Flow

interface NoteItemRepository {
    suspend fun insertItem(item: NoteItemEntity)
    suspend fun deleteItem(item: NoteItemEntity)
    fun getAllItems(): Flow<List<NoteItemEntity>>
    suspend fun getNoteItemById(id: Int): NoteItemEntity
}
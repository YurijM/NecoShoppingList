package com.mu.necoshoppinglist.data.repository

import com.mu.necoshoppinglist.data.dao.NoteItemDao
import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import kotlinx.coroutines.flow.Flow

class NoteItemRepositoryImpl(
    private val dao: NoteItemDao
) : NoteItemRepository{
    override suspend fun insertItem(item: NoteItemEntity) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItemEntity) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<NoteItemEntity>> {
        return getAllItems()
    }

    override fun getNoteItemById(id: Int): NoteItemEntity {
        return getNoteItemById(id)
    }
}
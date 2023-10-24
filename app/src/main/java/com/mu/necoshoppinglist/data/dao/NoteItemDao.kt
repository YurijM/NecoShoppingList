package com.mu.necoshoppinglist.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mu.necoshoppinglist.data.entity.NoteItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: NoteItemEntity)

    @Delete
    suspend fun deleteItem(item: NoteItemEntity)

    @Query("SELECT * FROM note_item_table")
    fun getAllItems(): Flow<NoteItemEntity>

    @Query("SELECT * FROM note_item_table " +
            "WHERE id = :id")
    fun getNoteItemById(id: Int): NoteItemEntity
}
package com.mu.necoshoppinglist.di

import android.app.Application
import androidx.room.Room
import com.mu.necoshoppinglist.data.MainDb
import com.mu.necoshoppinglist.data.repository.AddItemRepository
import com.mu.necoshoppinglist.data.repository.AddItemRepositoryImpl
import com.mu.necoshoppinglist.data.repository.NoteItemRepository
import com.mu.necoshoppinglist.data.repository.NoteItemRepositoryImpl
import com.mu.necoshoppinglist.data.repository.ShoppingListItemRepository
import com.mu.necoshoppinglist.data.repository.ShoppingListItemRepositoryImpl
import com.mu.necoshoppinglist.data_storage.DataStorageManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "shopping_list_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideShoppingListItemRepository(db: MainDb): ShoppingListItemRepository {
        return ShoppingListItemRepositoryImpl(db.shoppingListItemDao)
    }

    @Provides
    @Singleton
    fun provideAddItemRepository(db: MainDb): AddItemRepository {
        return AddItemRepositoryImpl(db.addItemDao)
    }

    @Provides
    @Singleton
    fun provideNoteItemRepository(db: MainDb): NoteItemRepository {
        return NoteItemRepositoryImpl(db.noteItemDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreManager(app: Application): DataStorageManager {
        return DataStorageManager(app)
    }
}
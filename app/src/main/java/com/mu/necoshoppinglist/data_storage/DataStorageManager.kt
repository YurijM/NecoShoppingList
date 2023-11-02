package com.mu.necoshoppinglist.data_storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

const val DATA_STORE_SHOPPING_TITLE_COLOR = "preference_storage_title_color"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(DATA_STORE_SHOPPING_TITLE_COLOR)
class DataStorageManager(private val context: Context) {
    suspend fun saveStringPreference(key: String, value: String) {
        context.dataStore.edit { preference ->
            preference[stringPreferencesKey(key)] = value
        }
    }

    fun getStringPreference(key: String, defValue: String) =
        context.dataStore.data.map { preference ->
            preference[stringPreferencesKey(key)] ?: defValue
        }

    companion object {
        const val TITLE_COLOR = "title_color"
    }
}
package com.mu.necoshoppinglist.screens.settings

sealed class SettingsEvent {
    data class OnItemSelected(val color: String) : SettingsEvent()
}

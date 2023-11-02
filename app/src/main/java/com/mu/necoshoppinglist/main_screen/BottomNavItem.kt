package com.mu.necoshoppinglist.main_screen

import com.mu.necoshoppinglist.R
import com.mu.necoshoppinglist.utils.Routes

sealed class BottomNavItem(
    val title: String,
    val iconId: Int,
    val route: String
) {
    object ListItem: BottomNavItem(
        title = "Покупки",
        iconId = R.drawable.ic_list,
        route = Routes.SHOPPING_LIST
    )
    object NoteItem: BottomNavItem(
        title = "Заметки",
        iconId = R.drawable.ic_note,
        route = Routes.NOTE_LIST
    )
    object Settings: BottomNavItem(
        title = "Настройки",
        iconId = R.drawable.ic_settings,
        route = Routes.SETTINGS
    )
    object About: BottomNavItem(
        title = "Инфо",
        iconId = R.drawable.ic_about,
        route = Routes.ABOUT
    )
}

package com.mu.necoshoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mu.necoshoppinglist.screens.about.AboutScreen
import com.mu.necoshoppinglist.screens.note_list.NoteListScreen
import com.mu.necoshoppinglist.screens.settings.SettingsScreen
import com.mu.necoshoppinglist.screens.shopping_list_screen.ShoppingListScreen
import com.mu.necoshoppinglist.utils.Routes

@Composable
fun NavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOPPING_LIST
    ) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen()
        }
        composable(Routes.NOTE_LIST) {
            NoteListScreen()
        }
        composable(Routes.ABOUT) {
            AboutScreen()
        }
        composable(Routes.SETTINGS) {
            SettingsScreen()
        }
    }
}
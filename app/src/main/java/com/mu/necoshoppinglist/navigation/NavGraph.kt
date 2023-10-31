package com.mu.necoshoppinglist.navigation

import androidx.compose.foundation.layout.PaddingValues
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
    navController: NavHostController,
    paddingValues: PaddingValues,
    onNavigate: (String) -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = Routes.SHOPPING_LIST
    ) {
        composable(Routes.SHOPPING_LIST) {
            ShoppingListScreen(paddingValues) { route ->
                onNavigate(route)
            }
        }
        composable(Routes.NOTE_LIST) {
            NoteListScreen(paddingValues) { route ->
                onNavigate(route)
            }
        }
        composable(Routes.ABOUT) {
            AboutScreen(paddingValues)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(paddingValues)
        }
    }
}
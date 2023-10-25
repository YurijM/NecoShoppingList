package com.mu.necoshoppinglist.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mu.necoshoppinglist.main_screen.MainScreen
import com.mu.necoshoppinglist.screens.add_item_screen.AddItemListScreen
import com.mu.necoshoppinglist.screens.new_note.NewNoteScreen
import com.mu.necoshoppinglist.utils.Routes

@Composable
fun NavGraphAdd() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.MAIN_SCREEN
    ) {
        composable(Routes.MAIN_SCREEN) {
            MainScreen(navController)
        }
        composable(
            Routes.ADD_ITEM_LIST + "/{listId}",
            arguments = listOf(
                navArgument("listId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
            ) {
            AddItemListScreen()
        }
        composable(Routes.NEW_NOTE) {
            NewNoteScreen()
        }
    }
}
package com.mu.necoshoppinglist.main_screen

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mu.necoshoppinglist.R
import com.mu.necoshoppinglist.navigation.NavGraph
import com.mu.necoshoppinglist.screens.shopping_list_screen.ShoppingListEvent
import com.mu.necoshoppinglist.screens.shopping_list_screen.ShoppingListViewModel
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.utils.UiEvent
import com.mu.necoshoppinglist.utils.dialog.MainDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navControllerAdd: NavHostController,
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    val snackbarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(uiEvent.message)
                }
                else -> {}
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        },
        bottomBar = {
            BottomNav(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = BlueMain,
                contentColor = Color.White,
                shape = RoundedCornerShape(50),
                onClick = {
                    viewModel.onEvent(ShoppingListEvent.OnShowEditDialog(null))
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add"
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        NavGraph(navController, paddingValues) { route ->
            navControllerAdd.navigate(route)
        }
        MainDialog(dialogController = viewModel)
    }
}
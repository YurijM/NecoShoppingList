package com.mu.necoshoppinglist.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.rememberNavController
import com.mu.necoshoppinglist.R
import com.mu.necoshoppinglist.navigation.NavGraph
import com.mu.necoshoppinglist.ui.theme.BlueMain

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNav(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                containerColor = BlueMain,
                contentColor = Color.White,
                shape = RoundedCornerShape(50),
                onClick = {}
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_add),
                    contentDescription = "Add"
                )
            }

        },
        floatingActionButtonPosition = FabPosition.Center
    ) {
        NavGraph(navController)
    }
}
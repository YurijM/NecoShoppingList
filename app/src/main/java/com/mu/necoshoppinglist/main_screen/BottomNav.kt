package com.mu.necoshoppinglist.main_screen

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.mu.necoshoppinglist.ui.theme.BlueLight
import com.mu.necoshoppinglist.ui.theme.BlueMain
import com.mu.necoshoppinglist.ui.theme.GrayLight

@Composable
fun BottomNav(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val listItems = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.Settings,
        BottomNavItem.About
    )
    BottomAppBar(
        containerColor = Color.White
    ) {
        listItems.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = BlueMain,
                    selectedTextColor = BlueMain,
                    unselectedIconColor = GrayLight,
                    unselectedTextColor = GrayLight,
                    indicatorColor = BlueLight
                ),
                selected = currentRoute == item.route,
                onClick = {
                    onNavigate(item.route)
                },
                icon = {
                       Icon(
                           painter = painterResource(id = item.iconId),
                           contentDescription = item.title
                       )
                },
                label = {
                    Text(text = item.title)
                },
                alwaysShowLabel = false
            )
        }
    }
}
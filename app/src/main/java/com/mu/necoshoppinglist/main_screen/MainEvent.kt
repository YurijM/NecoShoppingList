package com.mu.necoshoppinglist.main_screen


sealed class MainEvent{
    object OnItemSave: MainEvent()
    data class Navigate(val route: String): MainEvent()
    data class NavigateAdd(val route: String): MainEvent()
    data class OnNewItemClick(val route: String): MainEvent()
}

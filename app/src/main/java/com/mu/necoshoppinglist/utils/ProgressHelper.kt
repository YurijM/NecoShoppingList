package com.mu.necoshoppinglist.utils

object ProgressHelper {
    fun getProgress(
        allItemsCount: Int,
        selectedItemsCount: Int
    ): Float {
        return if (allItemsCount == 0) {
            0f
        } else {
            selectedItemsCount.toFloat() / allItemsCount.toFloat()
        }
    }
}
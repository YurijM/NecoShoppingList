package com.mu.necoshoppinglist.utils.dialog

sealed class DialogEvent {
    data class OnTextChange(val text: String) : DialogEvent()
    object OnCancel : DialogEvent()
    object OnOK : DialogEvent()
}

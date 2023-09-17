package com.example.techtask.app.feature.screen_chat.presentation

sealed class ChatState {
    object Init : ChatState()
    object Success : ChatState()
    class Error(val errorText: String) : ChatState()
}
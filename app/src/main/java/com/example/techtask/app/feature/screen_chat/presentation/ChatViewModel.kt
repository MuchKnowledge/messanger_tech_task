package com.example.techtask.app.feature.screen_chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.techtask.app.feature.screen_chat.domain.LoadMessagesUseCase
import com.example.techtask.app.feature.screen_chat.domain.SendMessageUseCase
import com.example.techtask.app.feature.screen_chat.presentation.models.MessageUi
import com.example.techtask.app.feature.screen_chat.presentation.models.toMessageUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatViewModel @Inject constructor(
    private val loadMessagesUseCase: LoadMessagesUseCase,
    private val sendMessageUseCase: SendMessageUseCase,
) : ViewModel() {

    val state: MutableStateFlow<ChatState> = MutableStateFlow(ChatState.Init)
    val messages: MutableStateFlow<List<MessageUi>> = MutableStateFlow(listOf())

    fun loadMessages() {
        viewModelScope.launch {
            loadMessagesUseCase().collectLatest {
                messages.value = it.map { item -> item.toMessageUi() }
            }
        }

        state.value = ChatState.Success
    }

    fun sendMessage(message: String) {
        viewModelScope.launch {
            sendMessageUseCase(message)
        }
    }
}
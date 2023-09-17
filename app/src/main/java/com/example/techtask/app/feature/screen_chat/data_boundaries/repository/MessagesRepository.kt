package com.example.techtask.app.feature.screen_chat.data_boundaries.repository

import com.example.techtask.app.feature.screen_chat.domain.models.MessageItem
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {

    suspend fun sendMessage(message: String)
    fun getMessages(): Flow<List<MessageItem>>
    fun cleanUp()
}
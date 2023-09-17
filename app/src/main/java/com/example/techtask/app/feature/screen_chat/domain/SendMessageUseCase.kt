package com.example.techtask.app.feature.screen_chat.domain

import com.example.techtask.app.feature.screen_chat.data_boundaries.repository.MessagesRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    suspend operator fun invoke(message: String) = messagesRepository.sendMessage(message)
}
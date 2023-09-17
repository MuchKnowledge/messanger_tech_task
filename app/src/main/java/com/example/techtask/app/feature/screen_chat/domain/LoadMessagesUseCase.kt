package com.example.techtask.app.feature.screen_chat.domain

import com.example.techtask.app.feature.screen_chat.data_boundaries.repository.MessagesRepository
import javax.inject.Inject

class LoadMessagesUseCase @Inject constructor(
    private val messagesRepository: MessagesRepository
) {

    operator fun invoke() = messagesRepository.getMessages()
}
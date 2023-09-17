package com.example.techtask.app.feature.screen_chat.presentation.models

import com.example.techtask.app.feature.screen_chat.domain.models.MessageItem

fun MessageItem.toMessageUi(): MessageUi {
    return MessageUi(
        id = id,
        message = message,
    )
}
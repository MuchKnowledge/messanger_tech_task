package com.example.techtask.app.feature.screen_chat.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.techtask.app.feature.screen_chat.presentation.models.MessageUi

class MessageDiffCallback : DiffUtil.ItemCallback<MessageUi>() {

    override fun areItemsTheSame(oldItem: MessageUi, newItem: MessageUi): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MessageUi, newItem: MessageUi): Boolean {
        return oldItem == newItem
    }
}

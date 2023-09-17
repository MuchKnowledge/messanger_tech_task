package com.example.techtask.app.feature.screen_chat.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.techtask.app.feature.screen_chat.presentation.models.MessageUi
import com.example.techtask.databinding.ItemMessageBinding

class MessagesAdapter : ListAdapter<MessageUi, MessagesAdapter.MessageViewHolder>(
    MessageDiffCallback()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(ItemMessageBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MessageViewHolder(
        private val binding: ItemMessageBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MessageUi) {

            with(binding) {
                textMessage.text = item.message
            }
        }
    }
}
package com.example.techtask.app.feature.screen_chat.data.repository

import com.example.techtask.app.feature.screen_chat.data_boundaries.repository.MessagesRepository
import com.example.techtask.app.feature.screen_chat.domain.models.MessageItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor() : MessagesRepository {

    private val repositoryScope = CoroutineScope(Dispatchers.Default)
    private val messageFlow = MutableStateFlow<List<MessageItem>>(listOf())

    private val nextId: Int
        get() = messageFlow.value.lastOrNull()?.id?.plus(1) ?: 0

    override fun getMessages(): Flow<List<MessageItem>> {
        return messageFlow
            .onStart {
                startProduceMessages()
            }.onCompletion {
                cleanUp()
            }
    }

    override suspend fun sendMessage(message: String) {
        messageFlow.value = messageFlow.value + MessageItem(
            id = nextId,
            message = "$message",
        )
    }

    override fun cleanUp() {
        repositoryScope.cancel()
    }

    private fun startProduceMessages() {
        repositoryScope.launch {
            timer(1000).onEach { addMessage() }.collect()
        }
        repositoryScope.launch {
            timer(2000).onEach { updateMessage() }.collect()
        }
    }

    private fun addMessage() {
        messageFlow.value = messageFlow.value + MessageItem(
            id = nextId,
            message = "Message $nextId",
        )
    }

    private fun updateMessage() {
        val item = messageFlow.value.random().let {
            it.copy(message = it.message.plus("+"))
        }

        messageFlow.value = messageFlow.value.map {
            if (it.id == item.id) item
            else it
        }
    }

    private fun timer(delay: Long): Flow<Int> {
        var timerCount = 0
        return flow {
            while (true) {
                Timber.d("Timber,in timer")
                delay(delay)
                emit(++timerCount)
            }
        }
    }
}
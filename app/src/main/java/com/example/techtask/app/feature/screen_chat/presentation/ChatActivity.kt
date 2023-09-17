package com.example.techtask.app.feature.screen_chat.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.techtask.R
import com.example.techtask.app.base.AppLoader
import com.example.techtask.app.common.binding.viewBinding
import com.example.techtask.app.common.presentation.VerticalSpacingItemDecoration
import com.example.techtask.app.feature.screen_chat.presentation.adapter.MessagesAdapter
import com.example.techtask.app.utils.extensions.errorToast
import com.example.techtask.app.utils.extensions.gone
import com.example.techtask.app.utils.extensions.hideKeyboardAndClearFocus
import com.example.techtask.app.utils.extensions.visible
import com.example.techtask.databinding.ActivityChatBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatActivity : AppCompatActivity(R.layout.activity_chat) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ChatViewModel

    private val binding by viewBinding<ActivityChatBinding>()
    private val messageAdapter by lazy { MessagesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initDependencies()
        initListeners()
        initRecycler()
        observeState()
    }

    private fun initDependencies() {
        (application as AppLoader)
            .appComponent
            .inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[ChatViewModel::class.java]
    }

    private fun initListeners() {
        with(binding) {
            buttonSend.setOnClickListener {
                viewModel.sendMessage(textInput.text.toString())
                textInput.text?.clear()
                hideKeyboardAndClearFocus()
            }
        }
    }

    private fun initRecycler() {
        with(binding.recyclerMessages) {
            adapter = messageAdapter
            addItemDecoration(VerticalSpacingItemDecoration(16))
        }
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collectLatest {
                when (it) {
                    ChatState.Init -> onInit()
                    ChatState.Success -> onSuccess()
                    is ChatState.Error -> onError(it.errorText)
                }
            }
        }
    }

    private fun onInit() {
        with(binding) {
            progress.visible()
            recyclerMessages.gone()
            textInput.gone()
        }

        viewModel.loadMessages()
    }

    private fun onSuccess() {
        with(binding) {
            progress.gone()
            recyclerMessages.visible()
            textInput.visible()
        }

        lifecycleScope.launch {
            viewModel.messages.collectLatest {
                messageAdapter.submitList(it)
            }
        }
    }

    private fun onError(errorText: String) {
        with(binding) {
            progress.gone()
            recyclerMessages.gone()
            textInput.gone()
        }

        errorToast(errorText)
    }
}
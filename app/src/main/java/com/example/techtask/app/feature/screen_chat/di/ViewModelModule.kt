package com.example.techtask.app.feature.screen_chat.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.techtask.app.common.di.ViewModelFactory
import com.example.techtask.app.common.di.ViewModelKey
import com.example.techtask.app.feature.screen_chat.presentation.ChatViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindMyViewModel(myViewModel: ChatViewModel): ViewModel

    // Add more ViewModels here
}
package com.example.techtask.app.feature.screen_chat.di

import com.example.techtask.app.feature.screen_chat.data.repository.MessagesRepositoryImpl
import com.example.techtask.app.feature.screen_chat.data_boundaries.repository.MessagesRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMessagesRepository(repository: MessagesRepositoryImpl): MessagesRepository
}
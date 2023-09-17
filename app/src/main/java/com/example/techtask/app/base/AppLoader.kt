package com.example.techtask.app.base

import AppComponent
import android.app.Application
import timber.log.Timber

class AppLoader : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        initDi()
        initTimber()
    }

    private fun initDi() {
        appComponent = DaggerAppComponent.builder().build()
    }

    private fun initTimber() {
        Timber.plant(Timber.DebugTree())
    }
}
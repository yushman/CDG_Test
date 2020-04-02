package com.example.cdg_test

import android.app.Application
import android.content.Context
import com.example.cdg_test.db.AppDatabase
import com.example.cdg_test.di.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            // declare used Android context
            androidContext(this@App)
            // declare modules
            modules(KoinModule().koinModule)
        }

        Timber.plant(Timber.DebugTree())
    }
}
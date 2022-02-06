package com.example.mvvmproject.app

import android.app.Application
import com.example.mvvmproject.di.adapterModule
import com.example.mvvmproject.di.networkModule
import com.example.mvvmproject.di.repositoryModule
import com.example.mvvmproject.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MainApp)
            modules(
                listOf(networkModule, repositoryModule, viewModelModule, adapterModule)
            )
        }

    }

}
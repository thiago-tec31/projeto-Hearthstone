package com.example.projecthearthstone

import android.app.Application
import com.example.projecthearthstone.core.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class AppHearthstone: Application() {


    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@AppHearthstone)
            modules(
                viewModelModule,
                usecaseModule,
                repositoryModule,
                mapperModule,
                networkModule,
                serviceModule
            )
        }
    }
}
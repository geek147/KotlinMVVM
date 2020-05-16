package com.envios.bukuwarungtest

import android.app.Application
import com.envios.bukuwarungtest.di.appModules
import com.envios.bukuwarungtest.di.dataModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class BukuWarungApp: Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@BukuWarungApp)
            modules(listOf(appModules, dataModules))
        }
    }
}
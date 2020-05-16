package com.envios.bukuwarungtest.di

import androidx.room.Room
import com.envios.bukuwarungtest.data.local.db.UserDb
import com.envios.bukuwarungtest.data.remote.BukuWarungFactory
import com.envios.bukuwarungtest.data.repository.BukuWarungRepository
import com.envios.bukuwarungtest.ui.home.HomeViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import org.koin.android.ext.koin.androidApplication



val appModules = module {
    single { BukuWarungFactory.create() }
    single { BukuWarungFactory.getHttpLoggingInterceptor() }
    single { BukuWarungFactory.getOkHttpClient(get()) }

    single { BukuWarungRepository(get(), get())}

    viewModel { HomeViewModel(get()) }
}

val dataModules = module {

    single { Room.databaseBuilder(androidApplication(), UserDb::class.java, "user.db").build() }
    single { get<UserDb>().userDao() }
}

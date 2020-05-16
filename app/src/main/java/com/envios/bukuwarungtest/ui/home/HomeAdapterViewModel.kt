package com.envios.bukuwarungtest.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.envios.bukuwarungtest.data.local.model.User
import com.envios.bukuwarungtest.data.remote.model.Data

class HomeAdapterViewModel(user: User?): ViewModel() {

    var name: ObservableField<String> = ObservableField()
    var imageUrl : ObservableField<String> = ObservableField()

    init {
        name.set(user?.firstName  + " "+ user?.lastName)
        imageUrl.set(user?.avatar)


    }
}
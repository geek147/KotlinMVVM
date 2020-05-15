package com.envios.bukuwarungtest.ui.home

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.envios.bukuwarungtest.model.Data

class HomeAdapterViewModel(data: Data?): ViewModel() {

    var name: ObservableField<String> = ObservableField()
    var imageUrl : ObservableField<String> = ObservableField()

    init {
        name.set(data?.firstName  + " "+ data?.lastName)
        imageUrl.set(data?.avatar)


    }
}
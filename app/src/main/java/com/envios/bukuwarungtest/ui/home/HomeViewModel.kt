package com.envios.bukuwarungtest.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.envios.bukuwarungtest.model.ResponseData
import com.envios.bukuwarungtest.data.repository.BukuWarungRepository

class HomeViewModel : ViewModel() {

    private val repository = BukuWarungRepository()

    var listUser: MutableLiveData<ResponseData> = MutableLiveData()
    var error: MutableLiveData<Throwable> = MutableLiveData()

    fun searchUsers() {
        repository.getUsers({listUser.postValue(it)},{error.postValue(it)})
    }


    override fun onCleared() {
        super.onCleared()
        repository.onDestroy()
    }
}
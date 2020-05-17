package com.envios.bukuwarungtest.ui.home

import android.content.ContentValues.TAG
import android.util.Log
import com.envios.bukuwarungtest.base.BaseViewModel
import com.envios.bukuwarungtest.data.local.model.User
import com.envios.bukuwarungtest.data.repository.BukuWarungRepository
import com.envios.bukuwarungtest.utils.Failed
import com.envios.bukuwarungtest.utils.Loading
import com.envios.bukuwarungtest.utils.ViewModelState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class HomeViewModel(private val repository: BukuWarungRepository) : BaseViewModel() {
    data class UsersLoaded(val userList: List<User>? = null) : ViewModelState()

    fun getUsers() {
        getUsersFromLocal()
        _states.value = Loading(true)
        launch {
            repository.getAllUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _states.value = Loading(false)
                }, {
                    _states.value = Loading(false)
                    var msg: String? = null
                    when (it) {
                        is java.net.UnknownHostException -> msg = "No Internet Connection"
                        else -> {
                            msg = it.localizedMessage
                            Log.e(TAG, msg)
                        }
                    }
                    _states.value = Failed(msg)
                })
        }
    }

    private fun getUsersFromLocal() {
        launch {
            repository.getAllUsersFromLocal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    _states.value = UsersLoaded(it)
                }, {
                    val msg: String = it.message.toString()
                    Log.d(TAG, msg)
                })
        }
    }
}
package com.envios.bukuwarungtest.data.base

import androidx.annotation.CallSuper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.envios.bukuwarungtest.utils.Failed
import com.envios.bukuwarungtest.utils.Loading
import com.envios.bukuwarungtest.utils.Success
import com.envios.bukuwarungtest.utils.ViewModelState
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private val disposable = CompositeDisposable()
    protected val _states = MutableLiveData<ViewModelState>()
    private val loading = Loading(false)
    private var failed = Failed()
    private var success = Success()
    val states: LiveData<ViewModelState>
        get() = _states

    fun launch(job: () -> Disposable) {
        disposable.add(job())

    }

    @CallSuper
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

    fun showLoading(isLoading: Boolean){
        loading.isLoading = isLoading
        _states.value = loading
    }

    fun showErrorMessage(message: String?=null,t: Throwable?=null){
        if(message!= null){
            failed.error = message
            _states.value = failed
        }

        if (t !=null){
            when (t){

                is java.net.UnknownHostException -> {
                    failed.error = "No Internet Connection"
                    _states.value = failed
                }

                else ->{
                    failed.error = t.message
                    _states.value = failed
                }
            }
        }

    }

    fun showSuccessMessage(message:String?=null){
        success.message = message
        _states.value = success
    }
}
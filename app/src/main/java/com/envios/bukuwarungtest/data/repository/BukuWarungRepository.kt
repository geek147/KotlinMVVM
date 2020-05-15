package com.envios.bukuwarungtest.data.repository

import com.envios.bukuwarungtest.model.ResponseData
import com.envios.bukuwarungtest.data.remote.BukuWarungFactory
import com.envios.bukuwarungtest.utils.ApiObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class BukuWarungRepository {

    private val mCompositeDisposable = CompositeDisposable()
    fun getUsers( onResult:(ResponseData) ->Unit, onError: (Throwable) -> Unit){
        BukuWarungFactory.create().getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object: ApiObserver<ResponseData>(mCompositeDisposable){
                override fun onApiSuccess(data: ResponseData) {

                    onResult(data)
                }

                override fun onApiError(er: Throwable) {
                    onError(er)
                }


            })

    }



    fun onDestroy() {
        mCompositeDisposable.clear()
    }
}
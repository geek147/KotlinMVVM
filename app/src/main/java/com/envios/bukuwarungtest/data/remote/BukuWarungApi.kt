package com.envios.bukuwarungtest.data.remote

import com.envios.bukuwarungtest.data.remote.model.ResponseData
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import retrofit2.http.GET
import retrofit2.http.Headers

interface BukuWarungApi {

    @GET("users")
    @Headers("Content-Type: application/json")
    fun getUsers(): Flowable<ResponseData>

}
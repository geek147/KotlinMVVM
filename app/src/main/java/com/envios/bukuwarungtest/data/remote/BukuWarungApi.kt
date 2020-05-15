package com.envios.bukuwarungtest.data.remote

import com.envios.bukuwarungtest.model.ResponseData
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers

interface BukuWarungApi {

    @GET("users")
    @Headers("Content-Type: application/json")
    fun getUsers(): Observable<ResponseData>

}
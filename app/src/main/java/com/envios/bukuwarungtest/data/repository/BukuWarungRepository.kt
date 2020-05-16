package com.envios.bukuwarungtest.data.repository

import androidx.room.Dao
import com.envios.bukuwarungtest.data.local.dao.UserDao
import com.envios.bukuwarungtest.data.local.model.User
import com.envios.bukuwarungtest.data.remote.BukuWarungApi
import com.envios.bukuwarungtest.data.remote.model.Data

import io.reactivex.Flowable


class BukuWarungRepository (val bukuWarungApi: BukuWarungApi, val userDao: UserDao) {

    fun getAllUsers(): Flowable<Data> {
        return bukuWarungApi.getUsers()
            .flatMap { Flowable.fromIterable(it.data) }
            .doOnNext { movie -> insertDataUser(movie) }

    }

    private fun insertDataUser(data: Data) {
        userDao.insert(
            User(
                id = data.id,
                avatar = data.avatar,
                email = data.email,
                firstName = data.firstName,
                lastName = data.lastName

            )
        )
    }

    fun getAllUsersFromLocal(): Flowable<List<User>> {
        return userDao.getAllUsers()
    }


}